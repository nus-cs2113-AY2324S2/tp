package seedu.binbash;

import seedu.binbash.parser.Parser;
import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;
import seedu.binbash.command.Command;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.BinBashException;

public class BinBash {
    private Ui userInterface;
    private ItemList itemList;
    private Parser inputParser;
    private Storage storage;

    public BinBash() {
        userInterface = new Ui();
        storage = new Storage();
        itemList = new ItemList(storage.loadData());
        inputParser = new Parser();
    }

    private void run() {
        userInterface.greet();

        while (userInterface.isUserActive()) {
            String userInput = userInterface.readUserCommand();
            try {
                Command userCommand = inputParser.parseCommand(userInput);

                if (userCommand instanceof ByeCommand) {
                    userInterface.setUserAsInactive();
                }

                userCommand.execute(itemList);
                userInterface.talk(userCommand.getExecutionUiOutput());

                if (userCommand.hasToSave()) {
                    storage.saveToStorage(itemList.getItemList());
                }
            } catch (BinBashException e) {
                userInterface.talk(e.getMessage());
            }
        }
    }
    
    /**
     * Main entry-point for the BinBash application.
     */
    public static void main(String[] args) {
        new BinBash().run();
    }
}
