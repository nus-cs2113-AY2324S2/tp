package seedu.duke;

public class InvalidDayException extends Exception {
    public InvalidDayException() {
        super("Invalid day. Please enter a day from Monday to Sunday.");
    }
}
