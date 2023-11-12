package co.hafid.speltutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class SpelRequestDto {

    private String spelExpression;
    private Map<String, Object> context;

}
