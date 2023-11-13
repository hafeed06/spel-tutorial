package co.hafid.speltutorial.service;

import co.hafid.speltutorial.dto.SpelRequestDto;
import co.hafid.speltutorial.dto.SpelResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;

@Service
public class SpelService {

    private static final String NULL_PARSED_OBJECT_MESSAGE = "Parsed object is null";

    @Autowired
    private ExpressionParser expressionParser;


    public SpelResultDto evaluate(SpelRequestDto request) throws EvaluationException, SpelParseException{
        Expression expression = expressionParser.parseExpression(request.getSpelExpression());
        EvaluationContext context = new StandardEvaluationContext(request);
        Object parsedObject = expression.getValue(context);
        var resultClass = getResultClass.apply(parsedObject);

        return SpelResultDto.builder().resultClass(resultClass).result(parsedObject).build();
    }

    private Function<Object, String> getResultClass =
            parsedObject -> Objects.nonNull(parsedObject) ? parsedObject.getClass().toString() : NULL_PARSED_OBJECT_MESSAGE;
}
