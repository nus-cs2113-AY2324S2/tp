package seedu.duke.exceptions;

public class FlirtForkEmptyException extends FlirtForkException{
    private static final String HORIZONTAL = "____________________________________________________________";
    public FlirtForkEmptyException() {
        super("Oh no! The task description is empty. \n" + HORIZONTAL);
    }

}
