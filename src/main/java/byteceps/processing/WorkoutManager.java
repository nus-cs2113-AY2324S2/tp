package byteceps.processing;


import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;

public class WorkoutManager extends ActivityManager {
    @Override
    public void execute(Parser parser) throws Exceptions.ErrorAddingActivity, Exceptions.ActivityExistsException, Exceptions.InvalidInput {
        switch (parser.getAction()) {
        case "create":
            Workout newWorkout = processCreateWorkout(parser);
            add(newWorkout);
            UserInterface.printMessage(String.format(
                    "Added Workout Plan: %s\n", newWorkout.getActivityName()
            ));
            break;
        case "assign":
            // similar to exercise's edit I think?
            // find the exercises available in the exercise manager (might need to pass in)
            // add to the workoutList in the workout class
            break;
        case "unassign":
            break;
        case "samples":
            list();
            break;
        case "list":
            if(parser.getActionParameter() == null) {
                list();
            } else {
                list(parser.getActionParameter());
            }
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    public Workout processCreateWorkout(Parser parser) throws Exceptions.InvalidInput {
        String workoutName = parser.getActionParameter();
        if (workoutName.isEmpty()) {
            throw new Exceptions.InvalidInput("Workout name cannot be empty");
        }
        return new Workout(parser.getActionParameter());
    }

    //todo: attempts to search for the workout name and lists that 1 workout
    public void list(String workoutName) {
        //add code here
    }
    @Override
    public String getActivityType(boolean plural) {
        return plural ? "Workouts" : "Workout";
    }
}
