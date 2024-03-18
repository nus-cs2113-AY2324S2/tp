package seedu.duke.exceptions;

public class CustomException extends Exception{
    public CustomException(String errorMessage) { // constructor
        super(errorMessage); // e.getMessage() to get this errorMessage
    }
}
