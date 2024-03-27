package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.activities.Exercise;
import byteceps.activities.TrackedExercise;
import byteceps.activities.TrackedWorkout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class TrackedWorkoutsManager extends ActivityManager {
    @Override
    public void execute(Parser parser) throws Exceptions.InvalidInput {
        throw new Exceptions.InvalidInput("RepsSetsManager is not meant to be executed");
    }

    public void addTrackedWorkout(String trackedWorkoutDate, String workoutName) {
        TrackedWorkout newTrackedWorkout = new TrackedWorkout(trackedWorkoutDate, workoutName);
        try {
            add(newTrackedWorkout);
        } catch (Exceptions.ActivityExistsException e) {
            // silently fail as duplicates are okay
        }
    }

    public void addTrackedExercise(String trackedWorkoutDate, String exerciseName,
                                   String weight, String sets, String repetitions)
            throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        try {
            int weightInt = Integer.parseInt(weight);
            int setsInt = Integer.parseInt(sets);
            int repsInt = Integer.parseInt(repetitions);

            TrackedExercise newTrackedExercise = new TrackedExercise(exerciseName, weightInt, setsInt, repsInt);
            TrackedWorkout trackedWorkout= (TrackedWorkout) retrieve(trackedWorkoutDate);

            trackedWorkout.addTrackedExercise(newTrackedExercise);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInput("Invalid reps/sets entered!");
        } catch (Exceptions.ActivityDoesNotExists e) {
            throw new Exceptions.ActivityDoesNotExists("The exercise is not in your workout for today!");
        }
    }

    public void list(String date, HashSet<Exercise> workoutHashSet) throws Exceptions.ActivityDoesNotExists {
        TrackedWorkout retrievedWorkout = (TrackedWorkout) retrieve(date);
        HashSet<TrackedExercise> trackedExercises = retrievedWorkout.getTrackedExercises();
        HashSet<Exercise> tempSet = new HashSet<>(workoutHashSet);
        StringBuilder result = new StringBuilder();
        result.append(String.format(
                "Listing Exercises on %s:%s",
                date, System.lineSeparator()));

        int index = 1;
        for (TrackedExercise currentTrackedExercise : trackedExercises) {
            String trackedName = currentTrackedExercise.getActivityName();
            int setCount = currentTrackedExercise.getSets();
            int repCount = currentTrackedExercise.getRepetitions();
            int weight = currentTrackedExercise.getWeight();
            result.append(String.format("\t\t\t%d. %s (weight: %d, sets: %d, reps: %d)\n",
                    index, trackedName, weight,
                    setCount, repCount)
            );

            tempSet.removeIf(p -> p.getActivityName().equals(trackedName));
            index++;
        }

        for (Exercise currentExercise : tempSet) {
            String exerciseName = currentExercise.getActivityName();
            result.append(String.format("\t\t\t%d. %s\n", index, exerciseName));
            index++;
        }

        UserInterface.printMessage(result.toString());
    }

    @Override
    public String getActivityType(boolean plural) {
        return "Tracked Workouts";
    }

    public JSONArray exportToJSON() {
        ArrayList<Activity> trackedWorkouts = getActivityList();
        JSONArray workouts = new JSONArray();
        for (Activity currentActivity : trackedWorkouts) {
            TrackedWorkout currentWorkout = (TrackedWorkout) currentActivity;
            String workoutDate = currentWorkout.getWorkoutDate();
            String workoutName = currentWorkout.getWorkoutName();

            HashSet<TrackedExercise> exercises = currentWorkout.getTrackedExercises();
            JSONObject workoutJson = getWorkoutJson(exercises, workoutName, workoutDate);

            workouts.put(workoutJson);
        }
        return workouts;
    }

    private static JSONObject getWorkoutJson(HashSet<TrackedExercise> exercises,
                                             String workoutName, String workoutDate) {
        JSONArray workoutExercises = new JSONArray();
        for (TrackedExercise currentExercise : exercises) {
            JSONObject exercise = new JSONObject();
            String exerciseName = currentExercise.getActivityName();

            exercise.put("exerciseName", exerciseName);
            exercise.put("weight", currentExercise.getWeight());
            exercise.put("sets", currentExercise.getSets());
            exercise.put("reps", currentExercise.getRepetitions());

            workoutExercises.put(exercise);
        }

        JSONObject workoutJson = new JSONObject();
        workoutJson.put("workoutDate", workoutDate);
        workoutJson.put("workoutName", workoutName);
        workoutJson.put("exercises", workoutExercises);
        return workoutJson;
    }

}
