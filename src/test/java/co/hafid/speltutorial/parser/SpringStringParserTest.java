package co.hafid.speltutorial.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SpringStringParserTest {

    private SpringStringParser parser;

    @BeforeEach
    void setUp() {
        parser = new SpringStringParser();
    }


    @Test
    void should_parse_string() {

        // Given
        String expression = "'Hello Man'";

        // When
        String parsedExpression = parser.parseExpression(expression);

        // Then
        assertThat(parsedExpression).as("The parsed Expression").isEqualTo("Hello Man");

    }

    @Test
    void should_concat_to_string() {

        // Given
        String expression = "'Hello Man'.concat(' How are you?')";

        // When
        String parsedExpression = parser.parseExpression(expression);

        // Then
        assertThat(parsedExpression).isEqualTo("Hello Man How are you?");

    }

    @Test
    void TDD() {
        // Given
        String expression = "T(java.time.LocalDate).now().toString()";

        // When
        String parsedExpression = parser.parseExpression(expression);

        // Then
        System.out.println(parsedExpression);
    }

}
