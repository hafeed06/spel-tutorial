package co.hafid.speltutorial.controller;

import co.hafid.speltutorial.dto.SpelRequestDto;
import co.hafid.speltutorial.dto.SpelResultDto;
import co.hafid.speltutorial.exception.SpelRequestException;
import co.hafid.speltutorial.service.SpelService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            throw new SpelRequestException(e.getMessage(), e.getCause());
        }
    }

}
