package exceptions;


/**
 * Represents the exception thrown when the grocery to edit does not exist.
 */
public class NoSuchGroceryException extends GitException {
    /**
     * Constructs NoSuchGroceryException.
     */
    public NoSuchGroceryException() {
        message = "The grocery does not exist!";
    }
}
