//@@author V4vern
package byteceps.activities;

import java.util.ArrayList;
import java.util.ListIterator;

public class Workout extends Activity {
    ArrayList<Exercise> exerciseList;

    public Workout(String workoutName) {
        super(workoutName);
        exerciseList = new ArrayList<>();
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public String toString(int numTabs) {
        assert numTabs > 0: "numTabs cannot be negative";

        StringBuilder result = new StringBuilder();
        result.append("\t".repeat(numTabs)).append(activityName).append(System.lineSeparator());
        for (ListIterator<Exercise> it = exerciseList.listIterator(); it.hasNext(); ) {
            Activity currentExercise = it.next();
            result.append("\t".repeat(numTabs + 1));
            result.append(String.format("%d. %s" + System.lineSeparator(),
                        it.nextIndex(), currentExercise.toString()));
        }
        return result.toString();
    }
}
