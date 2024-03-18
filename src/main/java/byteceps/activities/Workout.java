//@@author V4vern
package byteceps.activities;

import java.util.ArrayList;

public class Workout extends Activity {
    ArrayList<Exercise> workoutList;

    public Workout(String workoutName) {
        super(workoutName);
        workoutList = new ArrayList<>();
    }

    public ArrayList<Exercise> getWorkoutList() {
        return workoutList;
    }

    public void addExercise(Exercise exercise) {
        workoutList.add(exercise);
    }
}
