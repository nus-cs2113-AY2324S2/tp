package seedu.binbash.exceptions;

/**
 * Represents a command-specific error that occurred during BinBash execution.
 * Used when unsupported or mistyped commands are passed in by the User.
 */
public class InvalidCommandException extends BinBashException {

    /**
     * @param errorMessage A descriptive error message detailing the cause of the exception.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
