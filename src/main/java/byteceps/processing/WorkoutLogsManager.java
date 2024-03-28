package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.activities.Exercise;
import byteceps.activities.ExerciseLog;
import byteceps.activities.WorkoutLog;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class WorkoutLogsManager extends ActivityManager {
    @Override
    public void execute(Parser parser) throws Exceptions.InvalidInput {
        throw new Exceptions.InvalidInput("RepsSetsManager is not meant to be executed");
    }

    public void addWorkoutLog(String workoutLogDate, String workoutName) {
        WorkoutLog newWorkoutLog = new WorkoutLog(workoutLogDate, workoutName);
        try {
            add(newWorkoutLog);
        } catch (Exceptions.ActivityExistsException e) {
            // silently fail as duplicates are okay
        }
    }

    public void addExerciseLog(String workoutLogDate, String exerciseName,
                               String weight, String sets, String repetitions)
            throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        try {
            int weightInt = Integer.parseInt(weight);
            int setsInt = Integer.parseInt(sets);
            int repsInt = Integer.parseInt(repetitions);

            ExerciseLog newExerciseLog = new ExerciseLog(exerciseName, weightInt, setsInt, repsInt);
            WorkoutLog workoutLog = (WorkoutLog) retrieve(workoutLogDate);

            workoutLog.addExerciseLog(newExerciseLog);
        } catch (NumberFormatException e) {
            throw new Exceptions.InvalidInput("Invalid reps/sets entered!");
        } catch (Exceptions.ActivityDoesNotExists e) {
            throw new Exceptions.ActivityDoesNotExists("The exercise is not in your workout for today!");
        }
    }

    public void list(String date, HashSet<Exercise> workoutHashSet) throws Exceptions.ActivityDoesNotExists {
        WorkoutLog retrievedWorkout = (WorkoutLog) retrieve(date);
        HashSet<ExerciseLog> exerciseLogs = retrievedWorkout.getExerciseLogs();
        HashSet<Exercise> tempSet = new HashSet<>(workoutHashSet);
        StringBuilder result = new StringBuilder();
        result.append(String.format(
                "Listing Exercises on %s:%s",
                date, System.lineSeparator()));

        int index = 1;
        for (ExerciseLog currentExerciseLog : exerciseLogs) {
            String exerciseName = currentExerciseLog.getActivityName();
            int setCount = currentExerciseLog.getSets();
            int repCount = currentExerciseLog.getRepetitions();
            int weight = currentExerciseLog.getWeight();
            result.append(String.format("\t\t\t%d. %s (weight: %d, sets: %d, reps: %d)\n",
                    index, exerciseName, weight,
                    setCount, repCount)
            );

            tempSet.removeIf(p -> p.getActivityName().equals(exerciseName));
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
        return plural ? "Workout Logs" : "Workout Log";
    }

    public JSONArray exportToJSON() {
        ArrayList<Activity> workoutLogs = getActivityList();
        JSONArray workouts = new JSONArray();
        for (Activity currentActivity : workoutLogs) {
            WorkoutLog currentWorkout = (WorkoutLog) currentActivity;
            String workoutDate = currentWorkout.getWorkoutDate();
            String workoutName = currentWorkout.getWorkoutName();

            HashSet<ExerciseLog> exercises = currentWorkout.getExerciseLogs();
            JSONObject workoutJson = getWorkoutJson(exercises, workoutName, workoutDate);

            workouts.put(workoutJson);
        }
        return workouts;
    }

    private static JSONObject getWorkoutJson(HashSet<ExerciseLog> exercises,
                                             String workoutName, String workoutDate) {
        JSONArray workoutExercises = new JSONArray();
        for (ExerciseLog currentExercise : exercises) {
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
