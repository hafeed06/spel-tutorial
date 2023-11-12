package co.hafid.speltutorial.controller;

import co.hafid.speltutorial.dto.SpelRequestDto;
import co.hafid.speltutorial.dto.SpelResultDto;
import co.hafid.speltutorial.service.SpelService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SpelController {

    private final SpelService service;

    @PostMapping(value = "/evaluation", produces = "application/json")
    public ResponseEntity<SpelResultDto> evaluate(@RequestBody SpelRequestDto spelRequest) {
        try {
            return ResponseEntity.status(OK).body(service.evaluate(spelRequest));
        } catch (EvaluationException | SpelParseException e) {
            return ResponseEntity
                    .status(NOT_ACCEPTABLE)
                    .body(SpelResultDto.builder()
                            .error(SpelResultDto.SpelError.builder()
                                    .errorType(e.getClass().toString())
                                    .errorDetails(e.getMessage())
                                    .build())
                            .build());
        }
    }

}
