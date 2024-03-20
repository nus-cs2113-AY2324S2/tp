package seedu.stockpal;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;
import seedu.stockpal.storage.exception.StorageIOException;
import seedu.stockpal.ui.Ui;

public class StockPal {

    private static Parser parser;

    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */
    public static void main(String[] args) {
        start();
        runCommandUntilExit();
        exit();
    }

    private static void start() {
        Ui.printWelcomeMessage();
        try {
            Storage storage = new Storage();
            ProductList productList = storage.load();
            parser = new Parser(productList, storage);
        } catch (InvalidStorageFilePathException | StockPalException | StorageIOException e) {
            Ui.printToScreen(e.getMessage());
            exit();
        }
    }

    private static void exit() {
        Ui.printGoodbyeMessage();
        System.exit(0);
    }

    private static void runCommandUntilExit() {
        do {
            String userInput = Ui.getUserInput();
            try {
                Command command = parser.parseCommand(userInput);

                if (isExitCommand(command)) {
                    break;
                }
                command.execute();
            } catch (StockPalException spe) {
                Ui.printToScreen(spe.getMessage());
            }

        } while (true); // check if command is exit
    }

    private static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }
}
