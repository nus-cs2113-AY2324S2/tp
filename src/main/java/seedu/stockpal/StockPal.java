package seedu.stockpal;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.ListActionCommand;
import seedu.stockpal.commands.TransactionActionCommand;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.ui.Ui;

public class StockPal {
    private static Storage storage;
    private static Parser parser;
    private static ProductList productList;
    private static TransactionList transactionList =  new TransactionList();

    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */
    public static void main(String[] args) {
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
        productList.printLowQuantityProducts();
        Ui.printGoodbyeMessage();
        System.exit(0);
    }

    private static void runCommandUntilExit() {
        do {
            String userInput = Ui.getUserInput();
            try {
                Command command = parser.parseInput(userInput);
                checkCommandType(command);
                
            } catch (StockPalException spe) {
                Ui.printExceptionMessage(spe);
            }

        } while (true); // check if command is exit
    }

    /**
     * Checks the type of command.
     * @param command Command that the user entered.
     * @throws StockPalException throws exception.
     */
    private static void checkCommandType(Command command) throws StockPalException {
        if (isListActionCommand(command)) {
            ListActionCommand actionCommand = (ListActionCommand) command;
            actionCommand.execute(productList);
            storage.saveData(command, productList);
        } else if (isTransactionActionCommand(command)) {
            TransactionActionCommand actionCommand = (TransactionActionCommand) command;
            actionCommand.execute(productList, transactionList);
            storage.saveData(command, productList);
            //incomplete, save here for transactionList
        } else {
            command.execute();
        }
    }

    private static boolean isListActionCommand(Command command) {
        return command instanceof ListActionCommand;
    }

    private static boolean isTransactionActionCommand(Command command) {
        return command instanceof TransactionActionCommand;
    }

}
