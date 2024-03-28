package seedu.duke.exceptions;

/**
 * This class represents user Expections expected from the InputValidator class.
 */
public class InvalidUserException extends Exception{
    public InvalidUserException(String message) {
        super(message);
    }
}
