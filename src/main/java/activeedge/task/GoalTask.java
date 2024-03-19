package activeedge.task;

/**
 * Represents a task with a specific goal amount.
 * Extends the Task class.
 */
public class GoalTask extends Task {
    private int goalAmount; // The amount associated with the goal task.

    /**
     * Constructs a GoalTask object with the provided description and goal amount.
     *
     * @param description The description of the goal task.
     * @param goalAmount  The amount associated with the goal task.
     */
    public GoalTask(String description, int goalAmount) {
        super(description); // Calls the constructor of the superclass (Task) with the provided description.
        this.goalAmount = goalAmount; // Initializes the goal amount.
    }

    /**
     * Retrieves the goal amount associated with the task.
     *
     * @return The goal amount.
     */
    public int getGoalAmount() {
        return goalAmount;
    }

    /**
     * Overrides the toString method to provide a custom string representation of the GoalTask object.
     *
     * @return A string representation of the GoalTask object, including its description and goal amount.
     */
    @Override
    public String toString() {
        return "Goal " + this.getDescription() + " " + this.getGoalAmount();
    }
}
