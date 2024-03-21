package seedu.lifetrack.system.exceptions;

public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("\t Please ensure that you have keyed in the correct format!");
    }
}
