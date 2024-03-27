package byteceps.activities;

import java.util.HashSet;


public class TrackedWorkout extends Workout {
    protected final String workoutName;
    HashSet<TrackedExercise> trackedExercises;
    public TrackedWorkout(String workoutDate, String workoutName) {
        super(workoutDate);
        this.workoutName = workoutName;
        this.trackedExercises = new HashSet<>();
    }

    public void addTrackedExercise(TrackedExercise trackedExercise) {
        trackedExercises.remove(trackedExercise);
        trackedExercises.add(trackedExercise);
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getWorkoutDate() {
        return activityName;
    }

    public HashSet<TrackedExercise> getTrackedExercises() {
        return trackedExercises;
    }
}
