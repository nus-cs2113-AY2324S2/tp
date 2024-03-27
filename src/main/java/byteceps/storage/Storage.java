package byteceps.storage;

import byteceps.activities.Exercise;
import byteceps.activities.Workout;
import byteceps.errors.Exceptions;
import byteceps.processing.ExerciseManager;
import byteceps.processing.TrackedWorkoutsManager;
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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public void save(ExerciseManager allExercises, WorkoutManager allWorkouts,
                     WeeklyProgramManager weeklyProgram, TrackedWorkoutsManager trackedWorkoutsManager)
            throws IOException {
        JSONObject jsonArchive = new JSONObject().put("exerciseManager", allExercises.getActivityList().toArray());
        jsonArchive.put("workoutManager", allWorkouts.getActivityList().toArray());
        jsonArchive.put("weeklyProgram", weeklyProgram.exportToJSON());
        jsonArchive.put("trackedWorkoutsManager", trackedWorkoutsManager.exportToJSON());

        FileWriter fileWriter = new FileWriter(filePath.toFile());
        fileWriter.write(jsonArchive.toString());
        fileWriter.close();

        UserInterface.printMessage("All your workouts and exercises have been saved.");
    }

    public void load(ExerciseManager allExercises, WorkoutManager allWorkouts,
                     WeeklyProgramManager weeklyProgram, TrackedWorkoutsManager trackedWorkoutsManager)
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

        try (Scanner jsonScanner = new Scanner(jsonFile)) {
            JSONObject jsonArchive = new JSONObject(jsonScanner.nextLine());
            loadExercises(allExercises, jsonArchive);
            loadWorkouts(allExercises, allWorkouts, jsonArchive);
            loadWeeklyProgram(allWorkouts, weeklyProgram, jsonArchive);
            loadTrackedWorkouts(allExercises, allWorkouts, jsonArchive, trackedWorkoutsManager);
            UserInterface.printMessage("Data loaded successfully!");
        } catch (Exceptions.ActivityExistsException | Exceptions.ErrorAddingActivity |
             Exceptions.ActivityDoesNotExists | Exceptions.InvalidInput | JSONException | NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error processing JSON file");
        }

    }

    private static void loadWeeklyProgram(WorkoutManager allWorkouts, WeeklyProgramManager weeklyProgram,
        JSONObject jsonArchive)
            throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput, Exceptions.ActivityExistsException {
        JSONObject jsonWeeklyProgram = jsonArchive.getJSONObject("weeklyProgram");

        assert jsonWeeklyProgram.length() == 7 : "Weekly program array must be length 7";
        for (Iterator<String> it = jsonWeeklyProgram.keys(); it.hasNext(); ) {
            String day = it.next();
            String workout = (String) jsonWeeklyProgram.get(day);
            if (!workout.isBlank()) {
                Workout dayWorkout = (Workout) allWorkouts.retrieve(workout);
                weeklyProgram.assignWorkoutToDay(dayWorkout, day, true);
            }
        }
    }

    private static void loadWorkouts(ExerciseManager allExercises, WorkoutManager allWorkouts, JSONObject jsonArchive)
            throws Exceptions.ActivityExistsException, Exceptions.ErrorAddingActivity,
            Exceptions.ActivityDoesNotExists {
        JSONArray jsonWorkoutArray = jsonArchive.getJSONArray("workoutManager");
        for (int i = 0; i < jsonWorkoutArray.length(); i++) {
            JSONObject jsonWorkout = jsonWorkoutArray.getJSONObject(i);
            String workoutName = jsonWorkout.getString("activityName");
            Workout workout = new Workout(workoutName);
            allWorkouts.add(workout);
            JSONArray jsonExercisesInWorkout = jsonWorkout.getJSONArray("exerciseList");
            for (int j = 0; j < jsonExercisesInWorkout.length(); j++) {
                String exerciseInWorkout = jsonExercisesInWorkout.getJSONObject(j).getString("activityName");
                workout.addExercise((Exercise) allExercises.retrieve(exerciseInWorkout));
            }
        }
    }

    private static void loadExercises(ExerciseManager allExercises, JSONObject jsonArchive)
            throws Exceptions.ActivityExistsException, Exceptions.ErrorAddingActivity {
        JSONArray jsonExerciseArray = jsonArchive.getJSONArray("exerciseManager");
        for (int i = 0; i < jsonExerciseArray.length(); i++) {
            String exerciseName = jsonExerciseArray.getJSONObject(i).getString("activityName");
            allExercises.add(new Exercise(exerciseName));
        }
    }

    private void loadTrackedWorkouts(ExerciseManager allExercises, WorkoutManager allWorkouts,
                                     JSONObject jsonArchive, TrackedWorkoutsManager trackedWorkoutsManager)
            throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput {
        JSONArray jsonTrackedWorkouts = jsonArchive.getJSONArray("trackedWorkoutsManager");
        for (int i = 0; i < jsonTrackedWorkouts.length(); i++) {
            JSONObject currentWorkout = jsonTrackedWorkouts.getJSONObject(i);
            JSONArray exercisesArray = currentWorkout.getJSONArray("exercises");
            String workoutDate = currentWorkout.getString("workoutDate");
            String workoutName = currentWorkout.getString("workoutName");

            // verify workout exists
            if (allWorkouts.doesNotHaveActivity(workoutName)) {
                throw new Exceptions.ActivityDoesNotExists(
                        String.format("The workout %s does not seem to exist", workoutName)
                );
            }

            // create trackedWorkout entry
            trackedWorkoutsManager.addTrackedWorkout(workoutDate, workoutName);

            // loop through exercises
            for (int j = 0; j < exercisesArray.length(); j++) {
                JSONObject currentExercise = exercisesArray.getJSONObject(j);
                String exerciseName = currentExercise.getString("exerciseName");
                String weight = String.valueOf(currentExercise.getInt("weight"));
                String sets = String.valueOf(currentExercise.getInt(("sets")));
                String reps = String.valueOf(currentExercise.getInt(("reps")));

                // verify exercise exists
                if (allExercises.doesNotHaveActivity(exerciseName)) {
                    throw new Exceptions.ActivityDoesNotExists(
                            String.format("The exercise %s does not seem to exist", exerciseName)
                    );
                }

                trackedWorkoutsManager.addTrackedExercise(workoutDate, exerciseName,
                        weight, sets, reps);
            }
        }

    }


}
