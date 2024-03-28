package exceptions;

/**
 * Represents the exception thrown when the grocery has already expired.
 */
public class PastExpirationDateException extends GitException{
    /**
     * Constructs PastExpirationDateException.
     */
    public PastExpirationDateException() {
        message = "The grocery has already expired!";
    }
}
