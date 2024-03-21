package byteceps.processing;

import byteceps.activities.Exercise;
import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;

import java.util.ArrayList;

public class WorkoutManager extends ActivityManager {
    private final ExerciseManager exerciseManager;

    public WorkoutManager(ExerciseManager exerciseManager) {
        this.exerciseManager = exerciseManager;
    }

    //@@author V4vern
    @Override
    public void execute(Parser parser) throws Exceptions.ErrorAddingActivity,
            Exceptions.ActivityExistsException,
            Exceptions.InvalidInput,
            Exceptions.ActivityDoesNotExists {

        assert parser != null : "Parser must not be null";
        switch (parser.getAction()) {
        case "create":
            Workout newWorkout = processWorkout(parser);
            add(newWorkout);
            UserInterface.printMessage(String.format(
                    "Added Workout Plan: %s\n", newWorkout.getActivityName()
            ));
            break;
        case "delete":
            Workout existingWorkout = processWorkout(parser);
            delete(existingWorkout);
            UserInterface.printMessage(String.format(
                    "Deleted Exercise: %s", existingWorkout.getActivityName()
            ));
            break;
        case "assign":
            String workoutPlan = assignExerciseToWorkout(parser);
            UserInterface.printMessage(String.format(
                    "Assigned Exercise '%s' to Workout Plan '%s'\n", parser.getActionParameter(), workoutPlan
            ));
            break;
        case "unassign":
            String workoutName = unassignExerciseFromWorkout(parser);
            UserInterface.printMessage(String.format(
                    "Unassigned Exercise '%s' from Workout Plan '%s'\n", parser.getActionParameter(), workoutName
            ));
            break;
        case "info":
            list(parser.getActionParameter());
            break;
        case "list":
            list();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    //@@author V4vern
    public Workout processWorkout(Parser parser) throws Exceptions.InvalidInput {
        String workoutName = parser.getActionParameter();
        assert !workoutName.isEmpty(): "Workout name cannot be empty";
        if (workoutName.isEmpty()) {
            throw new Exceptions.InvalidInput("Workout name cannot be empty");
        }
        return new Workout(parser.getActionParameter());
    }

    //@@author V4vern
    public String assignExerciseToWorkout(Parser parser) throws Exceptions.InvalidInput,
            Exceptions.ActivityDoesNotExists {

        String exerciseName = parser.getActionParameter();
        assert exerciseName != null : "Exercise name cannot be null";
        String workoutPlanName = parser.getAdditionalArguments("to");
        assert workoutPlanName != null : "Workout plan name cannot be null";
        if (workoutPlanName == null) {
            throw new Exceptions.InvalidInput("assign command not complete");
        }

        Exercise exercise = (Exercise) exerciseManager.retrieve(exerciseName);
        assert exercise != null : "Exercise does not exist";
        Workout workoutPlan = (Workout) retrieve(workoutPlanName);
        assert workoutPlan != null : "Workout plan does not exist";

        if (workoutPlan.getExerciseList().contains(exercise)) {
            throw new Exceptions.InvalidInput("Exercise already assigned to workout plan");
        }

        workoutPlan.addExercise(exercise);

        return workoutPlanName;
    }

    //@@author V4vern
    public void list(String workoutPlanName) throws Exceptions.ActivityDoesNotExists {
        assert workoutPlanName != null : "Workout plan name cannot be null";
        Workout workout = (Workout) retrieve(workoutPlanName);
        assert workout != null : "Workout plan does not exist";
        StringBuilder message = new StringBuilder();
        ArrayList<Exercise> workoutList = workout.getExerciseList();

        message.append(String.format("Listing exercises in workout plan '%s':%n", workoutPlanName));

        int index = 1;
        for (Exercise exercise : workoutList) {
            message.append(String.format("\t\t\t%d. %s%n", index++, exercise.getActivityName()));
        }
        UserInterface.printMessage(message.toString());
    }

    //@@author V4vern
    public String unassignExerciseFromWorkout(Parser parser) throws Exceptions.InvalidInput,
            Exceptions.ActivityDoesNotExists {

        String workoutPlanName = parser.getAdditionalArguments("from");
        assert workoutPlanName != null : "Workout plan name cannot be null";
        String exerciseName = parser.getActionParameter();
        assert exerciseName != null : "Exercise name cannot be null";
        if (workoutPlanName == null) {
            throw new Exceptions.InvalidInput("unassign command not complete");
        }

        Workout workoutPlan = (Workout) retrieve(workoutPlanName);
        assert workoutPlan != null : "Workout plan does not exist";
        ArrayList<Exercise> workoutList = workoutPlan.getExerciseList();

        workoutList.removeIf(exercise -> exercise.getActivityName().equalsIgnoreCase(exerciseName));

        return workoutPlanName;
    }

    @Override
    public String getActivityType(boolean plural) {
        return plural ? "Workouts" : "Workout";
    }
}
