package seedu.duke;

/**
 * This class represents Expections expected from the InputValidator class.
 */
public class InvalidFormatException extends Exception{
    public InvalidFormatException(String message) {
        super(message);
    }
}
