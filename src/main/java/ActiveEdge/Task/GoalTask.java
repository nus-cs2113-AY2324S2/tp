package ActiveEdge.Task;

public class GoalTask extends Task {
    private int goalAmount;

    public GoalTask(String description, int goalAmount) {
        super(description);
        this.goalAmount = goalAmount;
    }

    public int getGoalAmount() {
        return goalAmount;
    }
}
