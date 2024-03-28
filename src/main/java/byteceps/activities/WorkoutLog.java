package byteceps.activities;

import java.util.HashSet;


public class WorkoutLog extends Activity {
    protected final String workoutName;
    HashSet<ExerciseLog> exerciseLogs;
    public WorkoutLog(String workoutDate, String workoutName) {
        super(workoutDate);
        this.workoutName = workoutName;
        this.exerciseLogs = new HashSet<>();
    }

    public void addExerciseLog(ExerciseLog exerciseLog) {
        exerciseLogs.remove(exerciseLog);
        exerciseLogs.add(exerciseLog);
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getWorkoutDate() {
        return activityName;
    }

    public HashSet<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }
}
