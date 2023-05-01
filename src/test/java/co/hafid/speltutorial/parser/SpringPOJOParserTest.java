package co.hafid.speltutorial.parser;

import java.util.stream.Stream;

import co.hafid.speltutorial.model.Car;
import co.hafid.speltutorial.model.DriveTrain;
import co.hafid.speltutorial.model.Spec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

class SpringPOJOParserTest {

    private SpringStringParser parser;

    private Car car;

    @BeforeEach
    void setUp() {
        parser = new SpringStringParser();
        car = Car.builder()
                .make("Apollo")
                .model("IE")
                .YearOfProduction(2013)
                .specs(Spec.builder().
                        speed(10)
                        .braking(9)
                        .acceleration(8)
                        .handling(7)
                        .launch(6)
                        .driveTrain(
                                DriveTrain.builder()
                                        .rearWheelDrive(true)
                                        .turboEngine(true)
                                        .build()
                        )
                        .build())
                .build();
    }

    @Test
    void parse_car_object() {
        // Given

        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("model");

        EvaluationContext context = new StandardEvaluationContext(car);

        // When
        String model = (String) expression.getValue(context);

        // Then
        assertThat(model).isEqualTo("IE");

    }

    @Test
    void parse_car_nested_object() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("specs.speed");

        EvaluationContext context = new StandardEvaluationContext(car);

        // When
        Integer speed = (Integer) expression.getValue(context);

        // Then
        assertThat(speed).isEqualTo(10);
    }

    @DisplayName("Checks if an Integer object is null, if it is returns -1, if it's not null returns its value")
    @ParameterizedTest
    @MethodSource("speedArguments")
    void parse_car_nested_object_that_is_absent(Integer speed, Integer expectedSpeed) {
        car.getSpecs().setSpeed(speed);
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("(specs?.speed != null) ? specs.speed : -1");

        EvaluationContext context = new StandardEvaluationContext(car);

        // When
        Integer parsedSpeed = (Integer) expression.getValue(context);

        // Then
        assertThat(parsedSpeed).as("Vehicle Speed or -1 if Vehicle speed is not defined")
                .isEqualTo(expectedSpeed);
    }


    @Test
    void parse_drivetrain_nested_object_that_is_boolean_and_might_be_null() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("(specs?.driveTrain?.rearWheelDrive ?: false)");

        EvaluationContext context = new StandardEvaluationContext(car);

        // When
        Boolean readWheelDriveEnabled = (Boolean) expression.getValue(context);

        // Then
        System.out.println(readWheelDriveEnabled);
    }

    @DisplayName(
            "Parse DriveTrain which is a boolean. " +
                    "If it doesn't exist or is null return 'Yes', otherwise return 'No'")
    @ParameterizedTest
    @MethodSource("driveTrainArguments")
    void parse_drivetrain_nested_object_that_is_boolean_and_response_with_yes_or_no(
            Boolean rearWheelDrive,
            String expectedResponse
    ) {
        car.getSpecs().getDriveTrain().setRearWheelDrive(rearWheelDrive);

        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(
                "(specs?.driveTrain?.rearWheelDrive ?: false) ? 'Yes' : 'No'");

        EvaluationContext context = new StandardEvaluationContext(car);

        // When
        String readWheelDriveEnabled = (String) expression.getValue(context);

        // Then
        assertThat(readWheelDriveEnabled).isEqualTo(expectedResponse);
    }

    /* --------- Parameterized Tests Source Methods ------------- */
    private static Stream<Arguments> speedArguments() {
        return Stream.of(
                Arguments.of(10, 10),
                Arguments.of(null, -1)
        );
    }

    private static Stream<Arguments> driveTrainArguments() {
        return Stream.of(
                Arguments.of(null, "No"),
                        Arguments.of(false, "No"),
                        Arguments.of(true, "Yes")
                );
    }

}