package seedu.budgetbuddy;

public class Parser {
    public boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }
    public Boolean isMenuCommand(String input) {
        return input.startsWith("menu");
    }

    public Command handleMenuCommand(String input) {
        if (input.trim().equals("menu")) {
            return new MenuCommand(0);
        }
        try {
            int index = Integer.parseInt(input.substring(5));
            return new MenuCommand(index);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Command parseCommand(String input) {

        if (isMenuCommand(input)) {
            return handleMenuCommand(input);
        }

        return null;
    }

}
