package seedu.binbash;

import seedu.binbash.logger.MainLogger;
import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;
import seedu.binbash.command.Command;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.BinBashException;

import javax.imageio.stream.FileImageOutputStream;

public class BinBash {
    private Ui userInterface;
    private ItemList itemList;
    private Parser inputParser;
    private Storage storage;
    private MainLogger logger;

    public BinBash() {
        userInterface = new Ui();
        storage = new Storage();
        itemList = new ItemList(storage.loadData());
        inputParser = new Parser(itemList);

        try {
            logger = new MainLogger(BinBash.class.getName());
        } catch (BinBashException e) {
            userInterface.talk(e.getMessage());
        }
    }

    private void run() {
        try {
            logger.createLogFile();
        } catch (BinBashException e) {
            userInterface.talk(e.getMessage());
        }
        logger.info("Program started");

        userInterface.greet();

        while (userInterface.isUserActive()) {
            String userInput = userInterface.readUserCommand();
            try {
                Command userCommand = inputParser.parseCommand(userInput);

                if (userCommand instanceof ByeCommand) {
                    userInterface.setUserAsInactive();
                }

                userCommand.execute();
                userInterface.talk(userCommand.getExecutionUiOutput());
                storage.saveToStorage(itemList.getItemList());

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
