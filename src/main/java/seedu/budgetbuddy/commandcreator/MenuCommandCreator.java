package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.MenuCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuCommandCreator extends CommandCreator {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String userInput;

    public MenuCommandCreator(String userInput) {
        this.userInput = userInput;
    }

    public Boolean isEmptyMenuCommand() {
        return userInput.trim().equals("menu");
    }

    /**
     * Processes all menu commands and returns the corresponding Command object.
     * This method interprets the user's input and displays either the entire menu
     * or the associated menu item
     *
     *
     * @return A new MenuCommand object with the specified index, returns null if
     *         index is not an integer
     */
    public Command handleNonEmptyMenuCommand() {
        try {
            String indexAsString = userInput.substring(5);
            int index = Integer.parseInt(indexAsString);

            LOGGER.log(Level.INFO, "Menu Command has found parameter" + index);
            return new MenuCommand(index);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Index found to not be an Integer");
            return null;
        }

    }
    public Command createMenuCommand() {
        assert userInput != null : "Input should not be empty";
        assert userInput.startsWith("menu") : "Input should be a menu command";

        if (isEmptyMenuCommand()) {
            LOGGER.log(Level.INFO, "Menu Command has no parameters");
            return new MenuCommand(0);
        }

        Command menuCommand = handleNonEmptyMenuCommand();
        return menuCommand;
    }

    @Override
    public Command createCommand() {
        return createMenuCommand();
    }

}
