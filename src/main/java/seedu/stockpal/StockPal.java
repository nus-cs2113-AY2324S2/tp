package seedu.stockpal;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.storage.exception.StorageIOException;
import seedu.stockpal.ui.Ui;

public class StockPal {
    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */

    private static final Storage STORAGE = new Storage();
    private static ProductList productList;
    private static Parser parser;

    public static void main(String[] args) {
        start();
        runCommandUntilExit();
        exit();
    }

    private static void start() {
        Ui.printWelcomeMessage();
        try {
            productList = STORAGE.load();
            parser = new Parser(productList);
        } catch (StockPalException | StorageIOException e) {
            throw new RuntimeException(e); //replace this with Ui.printError(error message);
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
            } catch (InvalidCommandException | InvalidFormatException e) {
                System.out.println("throw");
            }

        } while (true); // check if command is exit
    }

    private static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }
}
