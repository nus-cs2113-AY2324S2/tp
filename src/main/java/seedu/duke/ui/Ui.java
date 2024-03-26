package seedu.duke.ui;

import seedu.duke.CommandList;
import seedu.duke.Formatter;
import seedu.duke.Parser;
import seedu.duke.SyntaxAnalyser;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.exception.ArgumentMismatchException;
import seedu.duke.exception.BadTokenException;
import seedu.duke.exception.IllegalCommandException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ui {
    public static int roundCount = 1;
    public static final Scanner IN = new Scanner(System.in);
    private static boolean isRunning = true;
    private static String userInput;
    private static Parser userCommandReader;

    private static final Logger logger = Logger.getLogger("Foo");

    /**
     * Reads user input and stores it
     */
    public static void beginListening() {
        logger.log(Level.INFO, "going to start processing user input");
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
            logger.log(Level.WARNING, "wrong command error");
            throw new ProcessInputException();
        } catch (ArgumentMismatchException e1) {
            int userArgumentCount = e1.userArgumentCount;
            int correctArgumentCount = SyntaxAnalyser.getArgumentCount(e1.userCommandName);
            Formatter.printErrorArgumentsMismatch(e1.userCommandName, userArgumentCount, correctArgumentCount);
            logger.log(Level.WARNING, "wrong user input format error");
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
        String readUserCommand = userCommandReader.getCommandName();
        String[] readArgumentTokens = userCommandReader.getArgumentTokens();

        CommandList selectedCommand = CommandList.valueOf(readUserCommand);

        switch (selectedCommand) {
        case BYE:
            CommandList.executeBye();
            break;
        case SHOOT:
            CommandList.executeShoot(readArgumentTokens);
            roundCount++;
            break;
        case PENALTY:
            CommandList.executePenalty();
            break;
        case SAVE:
            CommandList.executeSave();
            break;
            //insert new executable command here
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
