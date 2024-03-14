package seedu.lifetrack.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        System.out.println("Please ensure that you have keyed in the correct format!");
    }
}
