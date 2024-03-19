package exceptions;

public class InvalidBookIndexException extends RuntimeException {
    public InvalidBookIndexException(String message) {
        super(message);
    }
}

