package seedu.stockpal;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.storage.exception.StorageIOException;
import seedu.stockpal.ui.Ui;

import java.util.ArrayList;

public class StockPal {
    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */

    private static final Storage STORAGE = new Storage();
    private static ProductList productList;

    public static void main(String[] args) {
        start();
        runCommandUntilExit();
        exit();
    }

    private static void start() {
        Ui.printWelcomeMessage();
        try {
            productList = STORAGE.load();
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
                ArrayList<String> parsed = Parser.parseCommand(userInput);
                System.out.println(parsed.toString());
                //execute command and print results
            } catch (InvalidCommandException | InvalidFormatException e) {
                System.out.println("throw");
                break;
            }

        } while (true); // check if command is exit
    }
}
