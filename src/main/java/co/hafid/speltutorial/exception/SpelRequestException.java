package co.hafid.speltutorial.exception;

public class SpelRequestException extends RuntimeException{

    public SpelRequestException(String message) {
        super(message);
    }

    public SpelRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
