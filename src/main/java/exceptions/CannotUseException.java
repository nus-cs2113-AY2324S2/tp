package exceptions;

/**
 * Represents the exception thrown when command "use" should not be run due to the grocery an amount of 0.
 */
public class CannotUseException extends GitException {
    /**
     * Constructs CannotUseException.
     */
    public CannotUseException() {
        message = "The grocery you want to use is already out of stock - time to replenish!";
    }
}
