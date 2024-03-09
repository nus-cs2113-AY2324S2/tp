package seedu.duke;

public class Parser {
    public boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }

}
