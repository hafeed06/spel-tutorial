package co.hafid.speltutorial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Configuration
public class SpelConfiguration {

    @Bean
    public SpelParserConfiguration spelParserConfiguration() {
        return new SpelParserConfiguration(true, true);
    }

    @Bean
    public ExpressionParser expressionParser() {
        return new SpelExpressionParser(spelParserConfiguration());
    }

}
