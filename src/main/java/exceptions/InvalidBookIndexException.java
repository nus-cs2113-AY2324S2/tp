package exceptions;

public class InvalidBookIndexException extends NumberFormatException {
    public InvalidBookIndexException(String message) {
        super(message);
    }
}
