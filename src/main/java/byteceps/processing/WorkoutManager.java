package byteceps.processing;

import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;

public class WorkoutManager extends ActivityManager {
    @Override
    public void execute(Parser parser) throws Exceptions.ErrorAddingActivity, Exceptions.ActivityExistsException {
        switch (parser.getAction()) {
        case "create":
            Workout newWorkout = processCreateWorkout(parser);
            add(newWorkout);
            //   System.out.printf("Added exercise: %s\n", newExercise.getActivityName());
            break;
        case "assign":
            // similar to exercise's edit I think?
            // find the exercises available in the exercise manager (might need to pass in)
            // add to the workoutList in the workout class
            break;
        case "unassign":
            break;
        case "samples":
            break;
        case "list":
            list();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    public Workout processCreateWorkout(Parser parser) {
        // check if parser is valid if not throw errors
        return new Workout(parser.getActionParameter());
    }

    @Override
    public String getActivityType() {
        return "Workouts";
    }
}
