package byteceps.activities;

import java.util.ArrayList;

public class Workout extends Activity {
    ArrayList<Exercise> workoutList;

    public Workout(String workoutName) {
        super(workoutName);
        workoutList = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        workoutList.add(exercise);
    }
}
