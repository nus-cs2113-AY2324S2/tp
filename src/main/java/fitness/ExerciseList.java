package fitness;

import storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ExerciseList {

    private ArrayList<Exercise> allExercises = new ArrayList<>();
    private final String[] originalListForArms = {
        "Cable Triceps Push down,Arms,3,8", "Barbell Curls,Arms,3,8", "Preacher Curls,Arms,3,8",
        "Skullcrushers,Arms,3,8", "Lateral Raises,Arms,3,8"
    };
    private final String[] originalListForChest = {
        "Bench Press,Chest,3,8", "Incline Bench Press,Chest,3,8", "Diamond Push-up,Chest,3,15",
        "Cable Flies,Chest,3,8", "Wide Arm Push-up,Chest,3,15"
    };
    private final String[] originalListForAbs = {
        "Sit-ups,Abs,3,20", "Russian Twists,Abs,3,20", "Crunches,Abs,5,20",
        "Flutter Kicks,Abs,4,20", "Weighted Sit-Ups,Abs,3,20"
    };
    private final String[] originalListForBack = {
        "Pull Ups,Back,3,6", "Lateral Rows,Back,3,8", "Deadlift,Back,3,5",
        "Weighted Pull Ups,Back,3,6", "Cable Rows,Back,3,8"
    };
    private final String[] originalListForLegs = {
        "Weighted Squats,Legs,3,10", "Leg Press,Legs,3,8", "Leg Curl,Legs,3,8",
        "Leg Extensions,Legs,3,10", "Calf Raises,Legs,3,10"
    };

    public ExerciseList() {
        if (!Storage.isFileCreated(FitnessMotivator.FILE_PATH)) {
            initialiseData();
            Storage.saveTasksToFile(FitnessMotivator.FILE_PATH, allExercises);
        } else {
            parseData(Storage.loadDataFromFile(FitnessMotivator.FILE_PATH));
        }
    }

    private void initialiseData() {
        for (String s : originalListForArms) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == 4 : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], ExerciseType.ARMS,
                exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
        for (String s : originalListForChest) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == 4 : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], ExerciseType.CHEST,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
        for (String s : originalListForAbs) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == 4 : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], ExerciseType.ABS,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
        for (String s : originalListForBack) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == 4 : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], ExerciseType.BACK,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
        for (String s : originalListForLegs) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == 4 : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], ExerciseType.LEGS,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
    }

    private void parseData (ArrayList<String> data) {
        for (String s: data) {
            String[] parts = s.split(": |, | sets & | reps");
            if (parts.length == 4) {
                allExercises.add(newExercise(parts));
            }
        }
    }

    /**
     * This method adds an exercise object into the full list of exercises. It also sorts the list
     * in order of exercise type before saving it into storage.
     *
     * @param exercise An Exercise object for ExerciseList
     */
    public void add(Exercise exercise) {
        allExercises.add(exercise);

        // The comparing method extracts the exercise type from each exercise object and then
        // compares them based on their type
        Comparator<Exercise> comparator = Comparator.comparing(Exercise::getType);

        // The sort method then sorts the list based on the comparator specified before saving
        allExercises.sort(comparator);
        Storage.saveTasksToFile(FitnessMotivator.FILE_PATH, allExercises);
    }

    public Exercise get(ExerciseType type, int index) {
        ArrayList<Exercise> typeExercises = new ArrayList<>();
        for (Exercise e : allExercises) {
            assert e != null : "Invalid Exercise Detected";
            if (e.getType().equals(type)) {
                typeExercises.add(e);
            }
        }
        return typeExercises.get(index);
    }

    public int size(ExerciseType type) {
        int x = 0;
        for (Exercise e : allExercises) {
            if (e.getType().equals(type)) {
                x++;
            }
        }
        return x;
    }

    public Exercise newExercise(String[] parameters) {
        assert parameters.length == 4 : "Incorrect Parameters for a new Exercise Object";
        String type = parameters[0].toUpperCase();
        ExerciseType exerciseType = ExerciseType.valueOf(type);
        String exerciseName = parameters[1].trim();
        String sets = parameters[2].trim();
        String reps = parameters[3].trim();
        return new Exercise(exerciseName, exerciseType, sets, reps);
    }
}
