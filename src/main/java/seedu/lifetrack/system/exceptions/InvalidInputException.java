package seedu.lifetrack.system.exceptions;

public class InvalidInputException extends Exception {

    public final String heythere = "";

    public InvalidInputException(){
        super("\t Please ensure that you have keyed in the correct format!");
    }

    public InvalidInputException(String exception) {
        super(exception);
    }
}
