package exceptions;

/**
 * Represents the exception thrown when the format is correct, but input is empty.
 */
public class IncompleteCommandException extends GitException {
    /**
     * Constructs IncompleteCommandException.
     */
    public IncompleteCommandException(String parameter) {
        message = parameter + " cannot be empty!";
    }
}
