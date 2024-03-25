package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import fitness.ExerciseType;
import fitness.FitnessMotivator;

import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;

public class AddExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private String[] commandArgs;

    public AddExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = checkCommandArgs(commandArgs);
    }

    /**
     * Validates the command argument given for the fitness add command.
     *
     * @param commandArgs A string of arguments
     * @return A split array of strings of size 4 if there are no issues found with the string
     *         input
     *
     * @throws FitnessException Thrown when improper command arguments are found
     * */
    private String[] checkCommandArgs(String commandArgs) throws FitnessException {
        String[] tempCommandArgs = commandArgs.split(",", 4);

        // Handles insufficient parameters entered
        if (tempCommandArgs.length != REQUIRED_NUM_OF_PARAMETERS) {
            throw new FitnessException(
                    "Forgetting something? Key in the correct parameters please!");
        }

        // String Cleaning
        tempCommandArgs[0] = tempCommandArgs[0].trim();
        tempCommandArgs[1] = tempCommandArgs[1].trim();
        tempCommandArgs[2] = tempCommandArgs[2].trim();
        tempCommandArgs[3] = tempCommandArgs[3].trim();

        // Handles the case where non-integer values are entered in parameters that should only
        // be integers
        if (!tempCommandArgs[2].matches("\\d+") || !tempCommandArgs[3].matches("\\d+")) {
            throw new FitnessException("Did you enter your Sets and Reps correctly? :(");
        }

        // Checks that the entered type belongs to one of the ExerciseType Enum
        try {
            String exerciseTypeString = tempCommandArgs[0].toUpperCase().trim();
            ExerciseType.valueOf(exerciseTypeString);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Hmm...Invalid type of exercise..." + System.lineSeparator() +
                    "Only the following exercise types are allowed: " +
                    "Arms, Chest, Abs, Back and Legs!";
            throw new FitnessException(errorMessage);
        }
        return tempCommandArgs;
    }

    @Override
    public void execute() throws Wellness360Exception {
        fitnessMotivator.addExercises(commandArgs);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
