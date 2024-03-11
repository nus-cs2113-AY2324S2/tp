package stockPal;

import stockPal.commands.Command;
import stockPal.exceptions.InvalidCommandException;
import stockPal.exceptions.InvalidFormatException;
import stockPal.parser.Parser;
import stockPal.ui.Ui;

import java.util.ArrayList;

public class StockPal {
    /**
     * Main entry-point for the java.stockPal.StockPal application.
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
            try {
                ArrayList<String> parsedInput = Parser.parseCommand(userInput);
                // execute command and print results
            } catch (InvalidCommandException | InvalidFormatException e) {
                System.out.println("thrown");
            }

        } while (false); // check if command is exit
    }
}
