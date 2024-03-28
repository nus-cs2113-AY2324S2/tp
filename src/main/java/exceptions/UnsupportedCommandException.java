package exceptions;

public class UnsupportedCommandException extends RuntimeException {
    public UnsupportedCommandException(String message) {
        super(message);
    }
}
