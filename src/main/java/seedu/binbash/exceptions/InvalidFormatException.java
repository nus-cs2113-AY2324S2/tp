package seedu.binbash.exceptions;

/**
 * Represents a format-specific error that occurred during BinBash execution.
 * Used when User input is not formatted correctly (as per RegEx).
 */
public class InvalidFormatException extends BinBashException {

    /**
     * @param errorMessage A descriptive error message detailing the cause of the exception.
     */
    public InvalidFormatException(String errorMessage) {
        super(errorMessage);
    }
}
