package seedu.duke.exception;

public class ArgumentMismatchException extends Exception {
    public String userCommandName;
    public int userArgumentCount;

    public ArgumentMismatchException(String userCommandName, int userArgumentCount) {
        this.userCommandName = userCommandName;
        this.userArgumentCount = userArgumentCount;
    }
}
