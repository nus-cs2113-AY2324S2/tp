package seedu.binbash;

import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;
import seedu.binbash.command.Command;
import seedu.binbash.command.ByeCommand;

public class BinBash {
    private Ui userInterface;
    private ItemList itemList;
    private Parser inputParser;
    private Storage storage;

    public BinBash() {
        userInterface = new Ui();
        storage = new Storage();
        itemList = new ItemList(storage.loadData());
        inputParser = new Parser(itemList);
    }

    private void run() {
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
            storage.saveToStorage(itemList.getItemList());
        }

        userInterface.farewell();
    }
    
    /**
     * Main entry-point for the BinBash application.
     */
    public static void main(String[] args) {
        new BinBash().run();
    }
}
