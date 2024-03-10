package supertracker.parser;

import supertracker.command.Command;
import supertracker.command.ExitCommand;
import supertracker.command.InvalidCommand;

public class Parser {
    protected static final String EXIT_COMMAND = "quit";

    /**
     * Returns the command word specified in the user input string
     *
     * @param input a String of the user's input
     * @return a String of the first word in the user input
     */
    protected static String getCommandWord(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    /**
     * Parses a Command accordingly from the user input string
     *
     * @param input a String of the user's input
     * @return a Command to execute
     */
    public static Command parseCommand(String input) {
        String commandWord = getCommandWord(input);

        Command command;
        switch (commandWord) {
        case EXIT_COMMAND:
            command = new ExitCommand();
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

}
