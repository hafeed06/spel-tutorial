package co.hafid.speltutorial.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import co.hafid.speltutorial.model.Car;
import co.hafid.speltutorial.parser.data.CarsPopulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

class SpringPOJOListParserTest {

    private SpringStringParser parser;

    private SpelParserConfiguration spelConfig;

    public class Container {

        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }

        public List<Car> cars= new ArrayList<>();
    }

    private Container container = new Container();

    @BeforeEach
    void setUp() {
        parser = new SpringStringParser();
        spelConfig = new SpelParserConfiguration(true, true);
        container.cars.addAll(CarsPopulator.buildCarList());

    }


    @DisplayName("Get only the cars of a specific car maker")
    @Test
    void filter_cars_from_specific_maker() {
        // Given

        ExpressionParser expressionParser = new SpelExpressionParser(spelConfig);
        Expression expression = expressionParser.parseExpression("cars.?[make=='Apollo']");

        EvaluationContext context = new StandardEvaluationContext(container);

        // When
        List<Car> filteredCars = (ArrayList) expression.getValue(context);

        // Then
        assertThat(filteredCars)
                .hasSizeGreaterThan(0)
                .allMatch(car -> car.getMake().equals("Apollo"));
    }

    @DisplayName("Get Only the Cars of a specific Maker")
    @ParameterizedTest
    @MethodSource("makeModelsArguments")
    // Make here is used as a Spel Variable.
    void get_the_models_of_cars_from_maker(
            String make,
            String ...models
    ) {
        // Given
        ExpressionParser expressionParser = new SpelExpressionParser(spelConfig);
        Expression expression = expressionParser.parseExpression("cars.?[make==#make].![model]");

        EvaluationContext context = new StandardEvaluationContext(container);
        context.setVariable("make", make);

        // When
        List<String> filteredCars = (ArrayList) expression.getValue(context);

        // Then
        assertThat(filteredCars)
                .isNotEmpty()
                .containsAll(Arrays.asList(models));
    }

    @DisplayName("Safe Get the first car model from a specific maker or return default value if list is empty or no cars are provided")
    @Test
    void get_the_first_model_of_a_carMake_or_give_default_value() {
        // Given
        ExpressionParser expressionParser = new SpelExpressionParser(
                new SpelParserConfiguration(true, true)
        );

        Expression expression = expressionParser.parseExpression(
                "(#this?.cars?.?[make=='Maserati']?.isEmpty() ?: true) ? 'N/A' : #this.cars.[make=='Maserati']?.![model][0]"
        );

        EvaluationContext context = new StandardEvaluationContext(container);

        // When
        String firstCarModel = (String)  expression.getValue(context);
        assertThat(firstCarModel)
                .as("The first car models from a specific maker")
                .isEqualTo("MC12");
    }

    @DisplayName("Safe Get the first last model from a specific maker or return default value if list is empty or no cars are provided")
    @Test
    void get_the_last_model_of_a_carMake_or_give_default_value() {
        // Given
        ExpressionParser expressionParser = new SpelExpressionParser(
                new SpelParserConfiguration(true, true)
        );
        String carMake = "Apollo";

        Expression expression = expressionParser.parseExpression(
                "(#this?.cars?.?[make==#carMake]?.isEmpty() ?: true) ? 'N/A' : #this.cars.?[make==#carMake]?.![model][#this.cars" +
                        ".?[make==#carMake].size()-1]"
        );

        EvaluationContext context = new StandardEvaluationContext(container);
        context.setVariable("carMake", carMake);

        // When
        String lastCarModel = (String)  expression.getValue(context);
        assertThat(lastCarModel)
                .as("The last car model in the list from maker")
                .isEqualTo("EV");
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

    private static Stream<Arguments> makeModelsArguments() {
        return Stream.of(
                Arguments.of("Apollo", "IE", "EV"),
                Arguments.of("Maseratti", "MC12")
        );
    }

    private static void addCars(List<Car> carList, Car ...cars) {
       carList.addAll(Arrays.asList(cars));
    }

}