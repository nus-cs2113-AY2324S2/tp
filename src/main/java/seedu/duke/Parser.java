package seedu.duke;

import seedu.duke.exception.ArgumentMismatchException;
import seedu.duke.exception.BadTokenException;
import seedu.duke.exception.IllegalCommandException;
import java.util.logging.Logger;


public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private String commandName;
    private String[] argumentTokens = {};

    public Parser(String userInput) throws IllegalCommandException, ArgumentMismatchException, BadTokenException {
        //default case: User enters a valid command, the command expects no arguments
        //nameArgstrPair is either size 1 or 2
        final String[] nameArgstrPair = parseIntoNameAndArgumentString(userInput);
        assert nameArgstrPair.length == 1 || nameArgstrPair.length == 2;
        logger.info("Parsed command " + nameArgstrPair[0] +
                (nameArgstrPair.length == 1 ? " with no arguments": " with at least one argument"));
        final String userCommandName = getUserCommandName(nameArgstrPair);
        commandName = userCommandName.toUpperCase();
        final String userArgumentString;


        if (!SyntaxAnalyser.validateUserCommandName(userCommandName)) {
            commandName = "NOT_A_COMMAND";
            throw new IllegalCommandException();
        } else if (hasArgumentString(nameArgstrPair)) {
            userArgumentString = getUserArgumentString(nameArgstrPair);
            final String argSeparator = " /";
            argumentTokens = parseIntoTokens(userArgumentString, argSeparator);

            if (!isCorrectUserArgumentCount()) {
                throw new ArgumentMismatchException(commandName, argumentTokens.length);
            } else if (!SyntaxAnalyser.validateTokens(commandName, argumentTokens)) {
                throw new BadTokenException();
            }
        } else if (!isCorrectUserArgumentCount()) {
            throw new ArgumentMismatchException(commandName, argumentTokens.length);
        }
    }

    private static String getUserArgumentString(String[] nameArgstrPair) {
        return nameArgstrPair[1];
    }

    private static String getUserCommandName(String[] nameArgstrPair) {
        return nameArgstrPair[0];
    }

    private String[] parseIntoTokens(final String userArgumentString, final String separator) {
        final int expectedArgumentCount = SyntaxAnalyser.getArgumentCount(commandName);
        return userArgumentString.split(separator, expectedArgumentCount);
    }

    private boolean isCorrectUserArgumentCount() {
        int userArgumentCount = argumentTokens.length;
        int correctArgumentCount = SyntaxAnalyser.getArgumentCount(commandName);
        return correctArgumentCount == userArgumentCount;
    }

    private boolean hasArgumentString(String[] nameArgstrPair) {
        return nameArgstrPair.length == 2;
    }

    public static String[] parseIntoNameAndArgumentString(String userInput) {
        final String nameArgStrSeparator = " ";
        return userInput.split(nameArgStrSeparator, 2);
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgumentTokens() {
        return argumentTokens;
    }
}

