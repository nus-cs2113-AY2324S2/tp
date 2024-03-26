package seedu.duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyser {
    //check if argument token is an integer ranging from 0 to 2
    private static final String VALID_INDEX_REGEX = "^[0-2]$";
    //check if argument token is any keyboard character
    private static final String UNRESTRICTED_CHAR_LENGTH_REGEX = ".+";
    private static final String[][] lutRegexSeq = {
            {},
            {VALID_INDEX_REGEX},
            {},
            {VALID_INDEX_REGEX}, // Added regex pattern for penalty command
            {},
            {}
            //insert new command syntax here
    };

    /**
     * Returns the argument count for a given command
     *
     * @param commandName the command name of given command
     * @return The number of arguments for the command
     */
    public static int getArgumentCount(String commandName) {
        return getRegexSeq(commandName).length;
    }

    /**
     * Returns the list of regex expressions for a given command
     *
     * @param commandName the command name of given command
     * @return An array of regex strings used to match each parameter of a given input
     */
    public static String[] getRegexSeq(String commandName) {
        return lutRegexSeq[CommandList.valueOf(commandName).ordinal()];
    }

    /**
     * Returns true if the user entered command is valid, false otherwise
     *
     * @param userCommandName the command name of user entered command
     * @return True if user enters valid command
     */
    public static boolean validateUserCommandName(String userCommandName) {
        for (CommandList c : CommandList.values()) {
            if (c.name().toLowerCase().equals(userCommandName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if argument type of all arguments are correct, false otherwise
     *
     * @param commandName   the command name of given command
     * @param argumentTokens the list of arguments parsed from user input
     * @return True if all argument types are correct
     */
    public static boolean validateTokens(String commandName, String[] argumentTokens) {
        String[] cmdNameRegexSeq = getRegexSeq(commandName);
        for (int i = 0; i < getArgumentCount(commandName); i++) {
            Pattern pattern = Pattern.compile(cmdNameRegexSeq[i]);
            Matcher matcher = pattern.matcher(argumentTokens[i]);
            if (!matcher.find()) {
                Formatter.printErrorWrongArgumentType(commandName, cmdNameRegexSeq[i], i);
                return false;
            }
        }
        return true;
    }
}
