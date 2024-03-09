package seedu.duke;

public class Parser {
    public boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }

    public int parseCommand(String input) {
        if (input.startsWith("menu ")) {
            try {
                int index = Integer.parseInt(input.substring(5));
                return index;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

}
