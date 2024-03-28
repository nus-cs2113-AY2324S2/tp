package fitness;

public class Exercise {

    private String exerciseName;
    private ExerciseType exerciseType;
    private String sets;
    private String reps;

    public Exercise(String exerciseName, ExerciseType exerciseType, String sets, String reps) {
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.sets = sets;
        this.reps = reps;
    }

    public ExerciseType getType() {
        return exerciseType;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    @Override
    public String toString() {
        return exerciseType + ": " + exerciseName + ", " + sets + " sets & " + reps + " reps";
    }
}
