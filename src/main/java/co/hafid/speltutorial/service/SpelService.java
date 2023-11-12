package co.hafid.speltutorial.service;

import co.hafid.speltutorial.dto.SpelRequestDto;
import co.hafid.speltutorial.dto.SpelResultDto;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SpelService {

    private static final String NULL_PARSED_OBJECT_MESSAGE = "Parsed object is null";
    private SpelParserConfiguration spelConfig;


    public SpelResultDto evaluate(SpelRequestDto request) throws EvaluationException, SpelParseException {
        spelConfig = new SpelParserConfiguration(true, true);
        ExpressionParser expressionParser = new SpelExpressionParser(spelConfig);
        Expression expression = expressionParser.parseExpression(request.getSpelExpression());
        EvaluationContext context = new StandardEvaluationContext(request);
        Object parsedObject = expression.getValue(context);

        var resultClass = Objects.nonNull(parsedObject) ? parsedObject.getClass().toString() : NULL_PARSED_OBJECT_MESSAGE;
        SpelResultDto result = SpelResultDto.builder().resultClass(resultClass).result(parsedObject).build();
        return result;
    }

}
