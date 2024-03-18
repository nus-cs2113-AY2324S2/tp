package parser;

import commands.Command;
import commands.reflectcommands.GetReflectionQuestionsCommand;
import commands.reflectcommands.SaveToFavouritesCommand;
import exceptions.ReflectException;
import reflection.ReflectionManager;

public class ReflectionCommandParser {
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
        default:
            throw new ReflectException("Unknown reflect command");
        }
    }
}
