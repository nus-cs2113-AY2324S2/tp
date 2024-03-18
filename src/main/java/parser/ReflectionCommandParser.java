package parser;

import commands.Command;
import commands.reflectcommands.GetReflectionQuestionsCommand;
import commands.reflectcommands.ListFavouritesCommand;
import commands.reflectcommands.SaveToFavouritesCommand;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * A parser for handling reflection-related commands.
 */
public class ReflectionCommandParser {

    /**
     * Determines the appropriate reflection command based on user input.
     *
     * @param reflectionManager The ReflectionManager instance to be used for executing commands.
     * @param commandArgs       The string containing the user command and its arguments.
     * @return The Command object corresponding to the user's reflection command.
     * @throws ReflectException if an error occurs during command parsing or execution.
     */
    public static Command determineReflectionCommand(ReflectionManager reflectionManager, String commandArgs)
            throws ReflectException {

        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userReflectionCommand = userCommand[0];

        String reflectionCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch(userReflectionCommand) {
        case "get":
            return new GetReflectionQuestionsCommand(reflectionManager);
        case "save":
            return new SaveToFavouritesCommand(reflectionManager, reflectionCommandArgs);
        case "list":
            return new ListFavouritesCommand(reflectionManager);
        default:
            throw new ReflectException("Unknown reflect command");
        }
    }
}
