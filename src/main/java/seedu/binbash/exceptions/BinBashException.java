package seedu.binbash.exceptions;

/**
 * Represents an error that occurred during BinBash execution.
 */
public class BinBashException extends Exception {

    /**
     * @param errorMessage A descriptive error message detailing the cause of the exception.
     */
    public BinBashException(String errorMessage) {
        super(errorMessage);
    }
}
