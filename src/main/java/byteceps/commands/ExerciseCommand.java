package byteceps.commands;

import java.util.ArrayList;

import byteceps.exercises.Exercise;
import byteceps.exercises.ExerciseManager;

public class ExerciseCommand extends Command {
    public static final String COMMAND_WORD = "exercise";
    private final ExerciseManager exerciseManager;

    public ExerciseCommand(InputArguments commandAction, ArrayList<InputArguments> additionalArguments) {
        super(commandAction, additionalArguments);
        this.exerciseManager = ExerciseManager.getInstance();
    }


    @Override
    public CommandResult execute() {
        switch (getAction()) {
        case "add":
            return addExercise();
        case "delete":
            return deleteExercise();
        case "list":
            return listExercises();
        default:
            throw new UnsupportedOperationException();
        }
    }

    public CommandResult addExercise() {
        String exerciseName = getActionParameters();
        if (exerciseName != null && !exerciseName.isEmpty()) {
            exerciseManager.addExercise(exerciseName);
            return new CommandResult(String.format("Exercise '%s' added successfully.", exerciseName));
        } else {
            return new CommandResult("Exercise name cannot be empty.");
        }
    }

    public CommandResult deleteExercise() {
        String exerciseName = getActionParameters();
        if (exerciseName == null || exerciseName.isEmpty()) {
            return new CommandResult("Exercise name cannot be empty.");
        }

        if (exerciseManager.hasExercise(exerciseName)) {
            exerciseManager.deleteExercise(exerciseName);
            return new CommandResult(String.format("Exercise '%s' deleted successfully.", exerciseName));
        } else {
            return new CommandResult(String.format("Exercise '%s' does not exist.", exerciseName));
        }
    }

    public CommandResult listExercises() {
        ArrayList<Exercise> exercises = exerciseManager.getAllExercises();
        if (exercises.isEmpty()) {
            return new CommandResult("No exercises found.");
        } else {
            StringBuilder exerciseList = new StringBuilder("List of exercises:\n");
            for (Exercise exercise : exercises) {
                exerciseList.append(exercise.getExerciseName()).append("\n");
            }
            return new CommandResult(exerciseList.toString());
        }
    }
}
