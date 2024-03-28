package parser;

import commands.Command;
import commands.fitnesscommands.AddExerciseCommand;
import commands.fitnesscommands.GetExercisesCommand;
import exceptions.FitnessException;
import fitness.FitnessMotivator;

public class FitnessCommandParser {

    private static final int COMMAND_LENGTH = 2;
    public static Command determineFitnessCommand(FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {

        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFitnessCommand = userCommand[0].trim();

        String fitnessCommandArgs =
            userCommand.length == COMMAND_LENGTH ? userCommand[1].trim() : "";

        switch (userFitnessCommand) {
        case "get":
            return new GetExercisesCommand(fitnessMotivator, fitnessCommandArgs);
        case "add":
            return new AddExerciseCommand(fitnessMotivator, fitnessCommandArgs);
        default:
            throw new FitnessException("Unknown command. Can you type proper english please !-_-!");
        }
    }
}
