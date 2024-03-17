package byteceps.activities;

public class Exercise extends Activity {
    //    public String exerciseName;

    public Exercise(String exerciseName) {
        super(exerciseName);
    }

    //    public String getExerciseName() {
    //        return activityName;
    //    }

    public void editExerciseName(String newExerciseName) {
        activityName = newExerciseName;
    }
}
