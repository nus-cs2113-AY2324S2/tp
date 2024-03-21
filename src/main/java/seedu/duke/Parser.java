package seedu.duke;

import seedu.duke.exception.ArgumentMismatchException;
import seedu.duke.exception.BadTokenException;
import seedu.duke.exception.IllegalCommandException;

public class Parser {
    private String COMMAND_NAME;
    private String[] argumentTokens = {};

    public Parser(String userInput) throws IllegalCommandException, ArgumentMismatchException, BadTokenException {
        //default case: User enters a valid command, the command expects no arguments
        //NAME_ARGSTR_PAIR is either size 1 or 2
        final String[] NAME_ARGSTR_PAIR = parseIntoNameAndArgumentString(userInput);
        final String USER_COMMAND_NAME = getUserCommandName(NAME_ARGSTR_PAIR);
        COMMAND_NAME = USER_COMMAND_NAME.toUpperCase();
        final String USER_ARGUMENT_STRING;

        if (!SyntaxAnalyser.validateUserCommandName(USER_COMMAND_NAME)) {
            COMMAND_NAME = "NOT_A_COMMAND";
            throw new IllegalCommandException();
        } else if (hasArgumentString(NAME_ARGSTR_PAIR)) {
            USER_ARGUMENT_STRING = getUserArgumentString(NAME_ARGSTR_PAIR);
            final String ARG_SEPARATOR = " /";
            argumentTokens = parseIntoTokens(USER_ARGUMENT_STRING, ARG_SEPARATOR);

            if (!isCorrectUserArgumentCount()) {
                throw new ArgumentMismatchException(COMMAND_NAME, argumentTokens.length);
            } else if (!SyntaxAnalyser.validateTokens(COMMAND_NAME, argumentTokens)) {
                throw new BadTokenException();
            }
        } else if (!isCorrectUserArgumentCount()) {
            throw new ArgumentMismatchException(COMMAND_NAME, argumentTokens.length);
        }
    }

    private static String getUserArgumentString(String[] NAME_ARGSTR_PAIR) {
        return NAME_ARGSTR_PAIR[1];
    }

    private static String getUserCommandName(String[] NAME_ARGSTR_PAIR) {
        return NAME_ARGSTR_PAIR[0];
    }

    private String[] parseIntoTokens(final String USER_ARGUMENT_STRING, final String separator) {
        final int EXPECTED_ARGUMENT_COUNT = SyntaxAnalyser.getArgumentCount(COMMAND_NAME);
        return USER_ARGUMENT_STRING.split(separator, EXPECTED_ARGUMENT_COUNT);
    }

    private boolean isCorrectUserArgumentCount() {
        int userArgumentCount = argumentTokens.length;
        int correctArgumentCount = SyntaxAnalyser.getArgumentCount(COMMAND_NAME);
        return correctArgumentCount == userArgumentCount;
    }

    private static boolean hasArgumentString(String[] NAME_ARGSTR_PAIR) {
        return NAME_ARGSTR_PAIR.length == 2;
    }

    private static String[] parseIntoNameAndArgumentString(String userInput) {
        final String NAME_ARGSTR_SEPARATOR = " ";
        return userInput.split(NAME_ARGSTR_SEPARATOR, 2);
    }

    public String getCommandName() {
        return COMMAND_NAME;
    }

    public String[] getArgumentTokens() {
        return argumentTokens;
    }
}

