package seedu.stockpal;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.ListActionCommand;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.ui.Ui;

public class StockPal {
    private static Storage storage;
    private static Parser parser;
    private static ProductList productList;

    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */
    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";
        start();
        runCommandUntilExit();
    }

    private static void start() {
        Ui.printWelcomeMessage();
        try {
            storage = new Storage();
            productList = storage.load();
            parser = new Parser();
        } catch (StockPalException spe) {
            Ui.printExceptionMessage(spe);
            exit();
        }
    }

    public static void exit() {
        Ui.printGoodbyeMessage();
        System.exit(0);
    }

    private static void runCommandUntilExit() {
        do {
            String userInput = Ui.getUserInput();
            try {
                Command command = parser.parseCommand(userInput);

                if (!isListActionCommand(command)) {
                    command.execute();
                } else {
                    ListActionCommand actionCommand = (ListActionCommand) command;
                    actionCommand.execute(productList);
                    storage.saveData(command, productList);
                }
            } catch (StockPalException spe) {
                Ui.printExceptionMessage(spe);
            }

        } while (true); // check if command is exit
    }

    private static boolean isListActionCommand(Command command) {
        return command instanceof ListActionCommand;
    }
}
