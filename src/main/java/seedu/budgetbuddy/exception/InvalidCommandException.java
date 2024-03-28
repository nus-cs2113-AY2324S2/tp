package seedu.budgetbuddy.exception;

public class InvalidCommandException extends Exception{

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
