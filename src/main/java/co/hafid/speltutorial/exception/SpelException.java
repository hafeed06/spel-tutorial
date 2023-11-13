package co.hafid.speltutorial.exception;

import org.springframework.http.HttpStatus;

public record SpelException(String message, HttpStatus httpStatus) {
}
