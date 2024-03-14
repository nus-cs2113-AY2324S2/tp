package seedu.binbash;

import seedu.binbash.ui.Ui;
import seedu.binbash.command.Command;
import seedu.binbash.command.ByeCommand;

public class BinBash {
    /**
     * Main entry-point for the BinBash application.
     */
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        ItemList itemList = new ItemList();
        Parser inputParser = new Parser(itemList);

        userInterface.greet();

        while (userInterface.isUserActive()) {
            String userInput = userInterface.readUserCommand();
            Command userCommand = inputParser.parseCommand(userInput);

            if (userCommand instanceof ByeCommand) {
                userInterface.setUserAsInactive();
                continue;
            }

            String executionResult = userCommand.execute();
            userInterface.talk(executionResult);
        }

        userInterface.farewell();
    }
}
