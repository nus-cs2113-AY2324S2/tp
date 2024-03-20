package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyser {
    //check if argument token is an integer ranging from 0 to 2
    private static String VALID_INDEX_REGEX = "^[0-2]$";
    //    private static String getValidIndexRegex(int size) {
//    }
    //check if argument token is any keyboard character
    private static final String UNRESTRICTED_CHAR_LENGTH_REGEX = ".+";
    private static final String[][] lutRegexSeq = {
            {},
            {VALID_INDEX_REGEX},
//            {},
//            {VALID_INDEX_REGEX},
//            {VALID_INDEX_REGEX},
//            {UNRESTRICTED_CHAR_LENGTH_REGEX},
//            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX},
//            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX},
//            {VALID_INDEX_REGEX},
//            {UNRESTRICTED_CHAR_LENGTH_REGEX},
            //insert new command syntax here
    };

    /**
     * Returns the argument count for a given command
     *
     * @param COMMAND_NAME the command name of given command
     * @return The number of arguments for the command
     */
    public static int getArgumentCount(String COMMAND_NAME) {
        return getRegexSeq(COMMAND_NAME).length;
    }

    /**
     * Returns the list of regex expressions for a given command
     *
     * @param COMMAND_NAME the command name of given command
     * @return An array of regex strings used to match each parameter of a given input
     */
    public static String[] getRegexSeq(String COMMAND_NAME) {
        return lutRegexSeq[CommandList.valueOf(COMMAND_NAME).ordinal()];
    }

    /**
     * Returns true if the user entered command is valid, false otherwise
     *
     * @param USER_COMMAND_NAME the command name of user entered command
     * @return True if user enters valid command
     */
    public static boolean validateUserCommandName(String USER_COMMAND_NAME) {
        for (CommandList c : CommandList.values()) {
            if (c.name().toLowerCase().equals(USER_COMMAND_NAME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if argument type of all arguments are correct, false otherwise
     *
     * @param COMMAND_NAME   the command name of given command
     * @param argumentTokens the list of arguments parsed from user input
     * @return True if all argument types are correct
     */
    public static boolean validateTokens(String COMMAND_NAME, String[] argumentTokens) {
        String[] cmdNameRegexSeq = getRegexSeq(COMMAND_NAME);
        for (int i = 0; i < getArgumentCount(COMMAND_NAME); i++) {
            Pattern pattern = Pattern.compile(cmdNameRegexSeq[i]);
            Matcher matcher = pattern.matcher(argumentTokens[i]);
            if (!matcher.find()) {
                Formatter.printErrorWrongArgumentType(COMMAND_NAME, cmdNameRegexSeq[i], i);
                return false;
            }
        }
        return true;
    }
}
