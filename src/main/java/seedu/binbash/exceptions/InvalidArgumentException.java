package seedu.binbash.exceptions;

/**
 * Represents an argument-specific error that occurred during BinBash execution.
 * Used when User input does not contain valid arguments (e.g., provided list index is out of bounds).
 */
public class InvalidArgumentException extends InvalidCommandException {

    /**
     * @param errorMessage A descriptive error message detailing the cause of the exception.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
