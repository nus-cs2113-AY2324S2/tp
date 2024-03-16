package parser;

import commands.Command;
import commands.reflectcommands.GetReflectionQuestionsCommand;
import exceptions.ReflectException;
import reflection.ReflectionManager;

public class ReflectionCommandParser {
    public static Command determineReflectionCommand(ReflectionManager reflection, String commandArgs)
            throws ReflectException {

        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userReflectionCommand = userCommand[0];

        String reflectionCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch(userReflectionCommand) {
        case "get":
            return new GetReflectionQuestionsCommand(reflection);
        default:
            throw new ReflectException("Unknown reflect command");
        }
    }
}
