package byteceps.storage;

import byteceps.activities.Exercise;
import byteceps.activities.Workout;
import byteceps.errors.Exceptions;
import byteceps.processing.ExerciseManager;
import byteceps.processing.WeeklyProgramManager;
import byteceps.processing.WorkoutManager;
import byteceps.ui.UserInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public void save(ExerciseManager allExercises, WorkoutManager allWorkouts, WeeklyProgramManager weeklyProgram)
            throws IOException {
        JSONObject jsonArchive = new JSONObject().put("exerciseManager", allExercises.getActivityList().toArray());
        jsonArchive.put("workoutManager", allWorkouts.getActivityList().toArray());
        jsonArchive.put("weeklyProgram", weeklyProgram.getActivityList().toArray());

        FileWriter fileWriter = new FileWriter(filePath.toFile());
        fileWriter.write(jsonArchive.toString());
        fileWriter.close();

        UserInterface.printMessage("All your workouts and exercises have been saved.");
    }
    public void load(ExerciseManager allExercises, WorkoutManager allWorkouts, WeeklyProgramManager weeklyProgram)
            throws IOException {
        assert allExercises.getActivityList().isEmpty() && allWorkouts.getActivityList().isEmpty()
            && weeklyProgram.getActivityList().stream().allMatch(Objects::isNull)
            : "Must load from a clean state";

        File jsonFile = filePath.toFile();

        if (jsonFile.createNewFile()) {
            UserInterface.printMessage("Looks like you're starting fresh!");
            return;
        }

        UserInterface.printMessage("Loading your exercises...");

        try {
            Scanner jsonScanner = new Scanner(jsonFile);
            JSONObject jsonArchive = new JSONObject(jsonScanner.nextLine());
            loadExercises(allExercises, jsonArchive);
            loadWorkouts(allExercises, allWorkouts, jsonArchive);
            loadWeeklyProgram(allWorkouts, weeklyProgram, jsonArchive);

        } catch (Exceptions.ActivityExistsException | Exceptions.ErrorAddingActivity |
             Exceptions.ActivityDoesNotExists | Exceptions.InvalidInput | JSONException e) {
            throw new IOException("Error processing JSON file");
        }

    }

    private static void loadWeeklyProgram(WorkoutManager allWorkouts, WeeklyProgramManager weeklyProgram,
        JSONObject jsonArchive)
            throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput, Exceptions.ActivityExistsException {
        JSONArray jsonWeeklyProgram = jsonArchive.getJSONArray("weeklyProgram");
        assert jsonWeeklyProgram.length() == 7 : "Weekly program array must be length 7";
        for (int i = 0; i < 7; i++) {
            try {
                JSONObject jsonWorkout = jsonWeeklyProgram.getJSONObject(i);
                String workoutName = jsonWorkout.getString("activityName");
                Workout workout = (Workout) allWorkouts.retrieve(workoutName);
                weeklyProgram.assignWorkoutToDay(workout, i);
            } catch (JSONException ignored) {
                //ignored because jsonWeeklyProgram.getJSONObject(i) throws a JSONException
                //when the object is null, meaning there is no workout assigned to that day of the week.
            }
        }
    }

    private static void loadWorkouts(ExerciseManager allExercises, WorkoutManager allWorkouts, JSONObject jsonArchive)
            throws Exceptions.ActivityExistsException, Exceptions.ErrorAddingActivity,
            Exceptions.ActivityDoesNotExists {
        JSONArray jsonWorkoutArray = jsonArchive.getJSONArray("workoutManager");
        for (int i = 0; i < jsonWorkoutArray.length(); i += 1) {
            JSONObject jsonWorkout = jsonWorkoutArray.getJSONObject(i);
            String workoutName = jsonWorkout.getString("activityName");
            Workout workout = new Workout(workoutName);
            allWorkouts.add(workout);
            JSONArray jsonExercisesInWorkout = jsonWorkout.getJSONArray("exerciseList");
            for (int j = 0; j < jsonExercisesInWorkout.length(); j += 1) {
                String exerciseInWorkout = jsonExercisesInWorkout.getJSONObject(j).getString("activityName");
                workout.addExercise((Exercise) allExercises.retrieve(exerciseInWorkout));
            }
        }
    }

    private static void loadExercises(ExerciseManager allExercises, JSONObject jsonArchive)
            throws Exceptions.ActivityExistsException, Exceptions.ErrorAddingActivity {
        JSONArray jsonExerciseArray = jsonArchive.getJSONArray("exerciseManager");
        for (int i = 0; i < jsonExerciseArray.length(); i += 1) {
            String exerciseName = jsonExerciseArray.getJSONObject(i).getString("activityName");
            allExercises.add(new Exercise(exerciseName));
        }
    }
}
