package seedu.stockpal;

import seedu.stockpal.commands.Command;
import seedu.stockpal.ui.Ui;

public class StockPal {
    /**
     * Main entry-point for the java.stockpal.StockPal application.
     */

    public static void main(String[] args) {
        start();
        runCommandUntilExit();
        exit();
    }

    private static void start() {
        // load storage file
        Ui.printWelcomeMessage();
    }

    private static void exit() {
        Ui.printGoodbyeMessage();
        System.exit(0);
    }

    private static void runCommandUntilExit() {
        Command command;
        do {
            String userInput = Ui.getUserInput();
            // command = new Parser.parseCommand(userInput);
            // execute command and print results
        } while (false); // check if command is exit
    }
}
