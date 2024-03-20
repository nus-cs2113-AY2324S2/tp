package exceptions;

/**
 * Represents the exception thrown when the grocery is not given after the command.
 */
public class EmptyGroceryException extends GitException {
    /**
     * Constructs EmptyGroceryException.
     */
    public EmptyGroceryException() {
        message = "A grocery needs to be specified!";
    }

}
