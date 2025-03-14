package psyduck.exception;

public class InsufficientArgsException extends RuntimeException {
    public InsufficientArgsException(String message) {
        super(message);
    }
}
