package recipeio;

import java.util.Arrays;

import static recipeio.Constants.INDEX_COMMAND;
import static recipeio.Constants.INDEX_ID;
import static recipeio.Constants.REGEX_MATCHER;


/**
 * Methods to parse input by the user.
 */
public class InputParser {

    /**
     * Returns command entered by the user, expected to be at beginning of string.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     * @return the command keyword. e.g. add, delete.
     */
    public static String parseCommand(String userInput) {
        return userInput.trim().split(" ")[INDEX_COMMAND];
    }

    /**
     * Returns index entered by the user, expected to be after the command.
     *
     * @param userInput input from the user in the command line.
     * @return the part of the user input after the command. e.g. 1
     */
    public static int parseID(String userInput) {
        String id = userInput.trim().split(" ")[INDEX_ID];
        return Integer.parseInt(id);
    }

    /**
     * Splits the description into components, such as name and time
     *
     * @param userInput input from the user in the command line.
     */
    public static String[] parseDetails(String userInput){
        String[] words = userInput.trim().split(" ");
        // Ignore the first word and join the remaining words into a string
        String remainingInput = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        return remainingInput.trim().split(REGEX_MATCHER);
    }

}
