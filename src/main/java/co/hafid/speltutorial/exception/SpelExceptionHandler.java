package co.hafid.speltutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpelExceptionHandler {

    @ExceptionHandler(SpelRequestException.class)
    public ResponseEntity<SpelException> handleSpelRequestException(SpelRequestException e) {
        SpelException spelException = new SpelException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(spelException, HttpStatus.NOT_ACCEPTABLE);
    }
}
