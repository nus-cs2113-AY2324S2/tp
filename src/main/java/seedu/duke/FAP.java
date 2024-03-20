package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.ui.Ui.printGreeting;

public class FAP {
    private static final Logger LOGGER = Logger.getLogger(FAP.class.getName());
    public static ModuleList moduleList = new ModuleList(10);

    public static void main(String[] args) {
        try {
            printGreeting();
            assert moduleList != null : "moduleList should not be null";
            runApplication();
        } catch (AssertionError e) {
            LOGGER.log(Level.SEVERE, "Assertion failed: " + e.getMessage(), e);
            System.err.println("Critical assertion failure: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred: " + e.getMessage(), e);
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void runApplication() {
        Scanner in = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                String userInput = in.nextLine().trim();
                LOGGER.log(Level.INFO, "User input: " + userInput);
                Command command = Parser.getCommand(userInput);
                command.execute(userInput);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
                System.out.println("An error occurred: " + e.getMessage());
                continueRunning = false; // Exit loop on error
            }
        }
    }
}
