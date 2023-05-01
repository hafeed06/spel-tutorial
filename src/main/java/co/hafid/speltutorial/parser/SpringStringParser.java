package co.hafid.speltutorial.parser;

import lombok.NoArgsConstructor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author abelb
 */
@NoArgsConstructor
public class SpringStringParser {

    private ExpressionParser parser = new SpelExpressionParser();

    Expression exp = parser.parseExpression("'Hello World'");
    String message = (String) exp.getValue();

    public String parseExpression(String expression) {
        return (String) parser.parseExpression(expression).getValue();
    }

}
