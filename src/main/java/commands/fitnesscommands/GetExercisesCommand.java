package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import fitness.ExerciseType;
import fitness.FitnessMotivator;

public class GetExercisesCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private ExerciseType exerciseType;

    public GetExercisesCommand (FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {
        this.fitnessMotivator = fitnessMotivator;
        exerciseType = checkCommandArgs(commandArgs);
    }

    /**
     * Validates the command argument given for the fitness get command. In this command, the
     * argument is optional.
     *
     * @param commandArgs An argument string
     * @return null if there is no argument provided, otherwise returns an Object of type
     *         ExerciseType for further processing
     *
     * @throws FitnessException Thrown when improper command arguments are found
     * */
    private ExerciseType checkCommandArgs(String commandArgs) throws FitnessException {
        String exerciseTypeString = commandArgs.toUpperCase().trim();

        // Checks if the arguments entered is empty
        if (exerciseTypeString.isEmpty() || exerciseTypeString.isBlank()) {
            return null;
        }

        // Checks that the entered type belongs to one of the ExerciseType Enum. If it is, return
        // the type, else throws an error
        try {
            return ExerciseType.valueOf(exerciseTypeString);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Hmm...Invalid type of exercise..." + System.lineSeparator() +
                    "Only the following exercise types are allowed: " +
                    "Arms, Chest, Abs, Back and Legs!";
            throw new FitnessException(errorMessage);
        }
    }


    @Override
    public void execute() throws Wellness360Exception {
        if (exerciseType == null) {
            fitnessMotivator.getExercises();
        } else {
            fitnessMotivator.getTypeExercises(exerciseType);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
