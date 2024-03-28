package exceptions;

public class InvalidCommandArgumentException extends IllegalArgumentException {
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
