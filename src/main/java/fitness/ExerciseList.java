package fitness;

import storage.Storage;

import java.util.ArrayList;
import java.util.Comparator;

import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;

/**
 * Represents the list of exercises and includes methods to manipulate the list
 * */
public class ExerciseList {

    private ArrayList<Exercise> allExercises = new ArrayList<>();

    // Constant lists used to initialise data into local machine
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

    /**
     * Checks if a save file exists, if it does then load it for use, else create a new data file
     * and initialise it with the data above.
     * */
    public ExerciseList() {
        if (!Storage.isFileCreated(FitnessMotivator.FILE_PATH)) {
            initialiseData();
            Storage.saveTasksToFile(FitnessMotivator.FILE_PATH, allExercises);
        } else {
            parseData(Storage.loadDataFromFile(FitnessMotivator.FILE_PATH));
        }
    }

    /**
     * Takes in a pre-saved list of exercises for a single exercise type
     *
     * @param list An array of strings that contain information about all exercises under each type
     * */
    private void initialiseSingleList(String[] list, ExerciseType type) {
        for (String s : list) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == REQUIRED_NUM_OF_PARAMETERS
                    : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], type,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
    }

    /**
     * Reads all 5 different string arrays from above and adds it into one ArrayList for use
     * */
    private void initialiseData() {
        initialiseSingleList(originalListForArms, ExerciseType.ARMS);
        initialiseSingleList(originalListForChest, ExerciseType.CHEST);
        initialiseSingleList(originalListForAbs, ExerciseType.ABS);
        initialiseSingleList(originalListForBack, ExerciseType.BACK);
        initialiseSingleList(originalListForLegs, ExerciseType.LEGS);
    }

    /**
     * Further parses data read from storage into usable exercise objects, before adding it into
     * the ArrayList.
     *
     * @param data An ArrayList of strings, comprising lines read from the data file
     * */
    private void parseData (ArrayList<String> data) {
        for (String s: data) {
            String[] parts = s.split(": |, | sets & | reps");
            if (parts.length == REQUIRED_NUM_OF_PARAMETERS) {
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

    /**
     * This method searches the ArrayList for Exercises that matches the required type, and returns
     * the n-th item of the queried type, where n is the index.
     *
     * @param type The ExerciseType Enum to be queried
     * @param index The n-th instance of all object that matches the queried ExerciseType
     *
     * @return Returns an Object of type Exercise that matches the type and index queried.
     * */
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

    public ArrayList<Exercise> getType(ExerciseType type) {
        ArrayList<Exercise> exercisesByType = new ArrayList<>();
        for (Exercise e : allExercises) {
            assert e != null : "Invalid Exercise Detected";
            if (e.getType().equals(type)) {
                exercisesByType.add(e);
            }
        }
        return exercisesByType;
    }

    /**
     * Returns the total number of a certain type of exercise
     *
     * @param type The ExerciseType Enum to be queried
     * */
    public int size(ExerciseType type) {
        int x = 0;
        for (Exercise e : allExercises) {
            if (e.getType().equals(type)) {
                x++;
            }
        }
        return x;
    }

    /**
     * Creates a new Exercise Object using an array of strings
     *
     * @param parameters An array of Strings that provide details for the creation of an Exercise
     *                   object.
     *                   Index 0 - Exercise Type
     *                   Index 1 - Exercise Name
     *                   Index 2 - Number of Sets
     *                   Index 3 - Number of Reps
     *
     * @return returns a new Exercise object
     * */
    public Exercise newExercise(String[] parameters) {
        assert parameters.length == REQUIRED_NUM_OF_PARAMETERS
            : "Incorrect Parameters for a new Exercise Object";

        String type = parameters[0].toUpperCase();
        ExerciseType exerciseType = ExerciseType.valueOf(type);
        String exerciseName = parameters[1].trim();
        String sets = parameters[2].trim();
        String reps = parameters[3].trim();

        return new Exercise(exerciseName, exerciseType, sets, reps);
    }
}
