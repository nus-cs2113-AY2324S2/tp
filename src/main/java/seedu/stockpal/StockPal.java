package seedu.stockpal;

import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.parser.Parser;
import seedu.stockpal.ui.Ui;

import java.util.ArrayList;

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
        do {
            String userInput = Ui.getUserInput();
            try {
                ArrayList<String> parsed = Parser.parseCommand(userInput);
                System.out.println(parsed.toString());
                // execute command and print results
            } catch (InvalidCommandException | InvalidFormatException e) {
                System.out.println("throw");
            }

        } while (true); // check if command is exit
    }
}
