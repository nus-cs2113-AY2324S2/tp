package byteceps.processing;


import byteceps.activities.Exercise;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;

public class ExerciseManager extends ActivityManager {
    @Override
    public void execute(Parser parser) throws Exceptions.InvalidInput,
            Exceptions.ErrorAddingActivity, Exceptions.ActivityExistsException,
            Exceptions.ActivityDoesNotExists {
        if (parser.getAction().isEmpty()) {
            throw new Exceptions.InvalidInput("No action specified");
        }

        switch (parser.getAction()) {
        case "add":
            Exercise newExercise = processAddExercise(parser);
            add(newExercise);
            System.out.printf("Added exercise: %s\n", newExercise.getActivityName());
            break;
        case "delete":
            Exercise retrievedExercise = retrieveExercise(parser);
            delete(retrievedExercise);
            System.out.printf("Deleted exercise: %s\n", retrievedExercise.getActivityName());
            break;
        case "edit":
            edit();
            break;
        case "list":
            list();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    public Exercise processAddExercise(Parser parser) throws Exceptions.InvalidInput {
        String exerciseName = parser.getActionParameter();
        if (exerciseName.isEmpty()) {
            throw new Exceptions.InvalidInput("Exercise name cannot be empty");
        }
        return new Exercise(exerciseName);
    }

    public Exercise retrieveExercise(Parser parser) throws Exceptions.ActivityDoesNotExists {
        String exerciseName = parser.getActionParameter();
        return (Exercise) retrieve(exerciseName);
    }

    @Override
    public void edit() {
    }

    @Override
    public String getActivityType() {
        return "Exercise";
    }

    @Override
    public String toString() {
        return "Exercises";
    }
}
