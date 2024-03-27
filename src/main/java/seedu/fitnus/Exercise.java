package seedu.fitnus;

import seedu.fitnus.exception.UnregisteredExerciseException;

import java.util.HashMap;

public class Exercise {
    private static HashMap<String, int[]> exerciseDetails = new HashMap<>();
    private String name;
    private int duration; // Duration in minutes
    private ExerciseIntensity intensity;
    private int caloriesBurnt;

    // Constructor with only duration and exercise name
    public Exercise(String name, int duration, ExerciseIntensity intensity) throws UnregisteredExerciseException {
        assert name != null : "Name must not be null";
        this.name = name;
        assert duration > 0 : "Duration must be greater than 0";
        this.duration = duration;
        assert isValidIntensity(intensity) : "Intensity must be HIGH, MEDIUM, or LOW";
        this.intensity = intensity;
        setCaloriesBurnt(); // Assign exercise details based on the name and intensity
    }

    // Add exercise details to the static HashMap
    static {
        exerciseDetails.put("running", new int[]{8, 5, 3});
        exerciseDetails.put("cycling", new int[]{6, 4, 2});
        exerciseDetails.put("swimming", new int[]{10, 7, 4});
    }

    // Method to set exercise details based on exercise name
    private void setCaloriesBurnt() throws UnregisteredExerciseException {
        int[] details = exerciseDetails.get(name);
        if (details == null) {
            throw new UnregisteredExerciseException();
        }
        this.caloriesBurnt = duration * details[intensity.ordinal()];
    }

    private boolean isValidIntensity(ExerciseIntensity intensity) {
        return intensity == ExerciseIntensity.HIGH || intensity == ExerciseIntensity.MEDIUM ||
                intensity == ExerciseIntensity.LOW;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public ExerciseIntensity getIntensity() {
        return intensity;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter method for duration
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Setter method for intensity
    public void setIntensity(ExerciseIntensity intensity) {
        this.intensity = intensity;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    // Method to print exercise details
    public static void handleInfoExercise(String command) throws UnregisteredExerciseException {
        String name = Parser.parseInfoExercise(command);
        int[] details = exerciseDetails.get(name);
        if (details == null) {
            throw new UnregisteredExerciseException();
        }
        System.out.println("Exercise: " + name);
        System.out.println("Calories Burnt: ");
        System.out.println("HIGH intensity: " + details[0]);
        System.out.println("MEDIUM intensity: " + details[1]);
        System.out.println("LOW intensity: " + details[2]);
    }

    // Print all available exercises registered in the database
    public static void printAvailableExercises() {
        System.out.print("Available exercises: ");
        for (String exercise : exerciseDetails.keySet()) {
            System.out.print(exercise);
            System.out.print(", ");
        }
        System.out.print("etc.");
        System.out.println();
    }

    public static HashMap<String, int[]> getExerciseDetails() {
        return exerciseDetails;
    }
}
