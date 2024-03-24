package byteceps.activities;

public class Day extends Activity {
    protected Workout assignedWorkout;
    public Day(String activityName) {
        super(activityName);
    }

    public void setAssignedWorkout(Workout assignedWorkout) {
        this.assignedWorkout = assignedWorkout;
    }

    public Workout getAssignedWorkout() {
        return this.assignedWorkout;
    }
}
