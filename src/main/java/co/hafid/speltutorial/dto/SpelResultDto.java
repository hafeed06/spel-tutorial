package co.hafid.speltutorial.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpelResultDto {

    private String resultClass;
    private Object result;
    private SpelError error;

    @Getter
    @Builder
    public static class SpelError {
        private String errorType;
        private String errorDetails;
    }
}
