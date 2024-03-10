package seedu.budgetbuddy;

public class Parser {
    public boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }

    /**
     * Checks if the provided input starts with the word "menu" .
     *
     * @param input The user input string
     * @return true if user input starts with "menu", else returns false
     */
    public Boolean isMenuCommand(String input) {
        return input.startsWith("menu");
    }

    /**
     * Processes all menu commands and returns the corresponding Command object
     * This method interprets the user's input and displays either the entire menu or the associated menu item
     *
     * @param input The full user input string
     * @return A new MenuCommand object with the specified index, returns null if index is not an integer
     */
    public Command handleMenuCommand(String input) {
        if (input.trim().equals("menu")) {
            return new MenuCommand(0);
        }
        try {
            String indexAsString = input.substring(5);
            int index = Integer.parseInt(indexAsString);
            return new MenuCommand(index);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses a string input into a Command object and returns the associated command to handle the associated user input
     * @param input The user input string.
     * @return A Command object corresponding to the user input, or null if the input is invalid.
     */
    public Command parseCommand(String input) {

        if (isMenuCommand(input)) {
            return handleMenuCommand(input);
        }

        return null;
    }

}
