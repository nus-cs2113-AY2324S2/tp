package command;

import activeedge.task.GoalTask;
import static activeedge.task.TaskList.tasksList;
import activeedge.ui.GoalsUi;

/**
 * The AddGoalsCommand class represents a command to add a goal task to a task list.
 * It encapsulates the necessary information required to execute the command.
 */
public class AddGoalsCommand {

    private String description; // Description of the goal task
    private int goalAmount; // The amount associated with the goal task

    /**
     * Constructs an AddGoalsCommand object with the specified description and goal amount.
     *
     * @param description the description of the goal task
     * @param goalAmount the amount associated with the goal task
     */
    public AddGoalsCommand(String description, int goalAmount) {
        this.description = description;
        this.goalAmount = goalAmount;
    }

    /**
     * Executes the command by creating a new GoalTask with the provided description and goal amount,
     * adding it to the tasks list, and printing a message to indicate that the goal task has been added.
     */
    public void execute() {
        // Create a new GoalTask
        GoalTask addGoalTask = new GoalTask(description, goalAmount);
        // Add the task to the tasks list
        tasksList.add(addGoalTask);
        // Print a message to indicate that the goal task has been added
        GoalsUi.printAddGoalMessage(description, goalAmount);
    }
}
