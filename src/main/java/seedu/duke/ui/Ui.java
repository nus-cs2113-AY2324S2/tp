package seedu.duke.ui;

import seedu.duke.CommandList;
import seedu.duke.Formatter;
import seedu.duke.Parser;
import seedu.duke.SyntaxAnalyser;

import seedu.duke.ProcessInputException;
import seedu.duke.ArgumentMismatchException;
import seedu.duke.BadTokenException;
import seedu.duke.IllegalCommandException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final Scanner IN = new Scanner(System.in);
    private static boolean isRunning = true;
    private static String userInput;
    private static Parser userCommandReader;
    public static int roundCount = 0;

    /**
     * Reads user input and stores it
     */
    public static void beginListening() {
        userInput = IN.nextLine();
    }

    /**
     * Parses read input into its parameters
     *
     * @throws ProcessInputException If command read from input is invalid
     */

    public static void processInput() throws ProcessInputException {
        try {
            userCommandReader = new Parser(userInput);
        } catch (IllegalCommandException e) {
            Formatter.printErrorWrongCommand();
            throw new ProcessInputException();
        } catch (ArgumentMismatchException e1) {
            int userArgumentCount = e1.userArgumentCount;
            int correctArgumentCount = SyntaxAnalyser.getArgumentCount(e1.userCommandName);
            Formatter.printErrorArgumentsMismatch(e1.userCommandName, userArgumentCount, correctArgumentCount);
            throw new ProcessInputException();
        } catch (BadTokenException e2) {
            Formatter.printErrorBadTokens();
            throw new ProcessInputException();
        }
    }

    /**
     * Runs command based on parsed input
     */
    public static void executeCommand() {
        CommandList selectedCommand = CommandList.valueOf(userCommandReader.getCommandName());

        switch (selectedCommand) {
        case BYE:
            CommandList.executeBye();
            break;
        case SHOOT:
            CommandList.executeShoot(userCommandReader);
            roundCount++;
            break;
        default:
            Formatter.printErrorUnknown();
        }
    }

    public static void setIsRunning(boolean runState) {
        isRunning = runState;
    }

    public static boolean getIsRunning() {
        return isRunning;
    }
}
