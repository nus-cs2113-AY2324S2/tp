package exceptions;

/**
 * Represents the exception thrown when the amount inputted by the user is invalid.
 */
public class InvalidAmountException extends GitException {
    /**
     * Constructs InvalidAmountException.
     */
    public InvalidAmountException() {
        message = "Please input a valid integer!";
    }
}
