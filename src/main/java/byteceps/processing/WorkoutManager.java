package byteceps.processing;

import byteceps.activities.Exercise;
import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;

import java.util.HashMap;

public class WorkoutManager extends ActivityManager {
    private final ExerciseManager exerciseManager;

    public WorkoutManager(ExerciseManager exerciseManager) {
        this.exerciseManager = exerciseManager;
    }

    @Override
    public void execute(Parser parser) throws Exceptions.ErrorAddingActivity, Exceptions.ActivityExistsException, Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        switch (parser.getAction()) {
        case "create":
            Workout newWorkout = processCreateWorkout(parser);
            add(newWorkout);
            UserInterface.printMessage(String.format(
                    "Added Workout Plan: %s\n", newWorkout.getActivityName()
            ));
            break;
        case "assign":
            String workoutPlan= assignExerciseToWorkout(parser);
            UserInterface.printMessage(String.format(
                    "Assigned Exercise '%s' to Workout Plan '%s'\n", parser.getActionParameter(), workoutPlan
            ));
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

    public String assignExerciseToWorkout(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        HashMap<String, String> additionalArguments = parser.getAdditionalArguments();
        if (!additionalArguments.containsKey("to")) {
            throw new Exceptions.InvalidInput("assign command not complete");
        }
        String exerciseName = parser.getActionParameter();
        String workoutPlanName = additionalArguments.get("to");

        Exercise exercise = (Exercise) exerciseManager.retrieve(exerciseName);
        Workout workoutPlan = (Workout) retrieve(workoutPlanName);

        workoutPlan.addExercise(exercise);

        return workoutPlanName;
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
