package exceptions;

/**
 * Represents the exception thrown when the LocalDate format is wrong.
 */
public class LocalDateWrongFormatException extends GitException {
    /**
     * Constructs LocalDateWrongFormatException.
     */
    public LocalDateWrongFormatException() {
        message = "Expiration date is in the wrong format. Please use yyyy-MM-dd.";
    }
}
