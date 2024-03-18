package parser;

import commands.Command;
import commands.fitnesscommands.GetExercisesCommand;
import exceptions.FitnessException;
import fitness.FitnessMotivator;

public class FitnessCommandParser {
    public static Command determineFitnessCommand(FitnessMotivator fitnessMotivator,
                                                  String commandArgs) throws FitnessException {
        String[] userCommand = commandArgs.trim().split("\\s+", 1);
        String userFitnessCommand = userCommand[0].trim();

        String fitnessCommandArgs = userCommand.length == 2 ? userCommand[1].trim() : "";

        switch(userFitnessCommand) {
        case "get":
            return new GetExercisesCommand(fitnessMotivator);
        default:
            throw new FitnessException("Unknown command");
        }
    }
}
