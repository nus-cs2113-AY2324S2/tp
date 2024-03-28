package command;

import activeedge.task.GoalTask;
import activeedge.task.Task;
import activeedge.task.TaskList;
import activeedge.ui.GoalsUi;

/**
 * The ShowGoalsCommand class represents a command to display the current goals set by the user.
 * It retrieves the calorie and water goals from the TaskList and prints them using the GoalsUi.
 */
public class ShowGoalsCommand {

    /**
     * Executes the command by iterating through the list of tasks,
     * retrieving the calorie and water goals, and printing them using GoalsUi.
     */
    public void execute() {
        int calorieGoal = 0; // The calorie goal set by the user
        int waterGoal = 0; // The water goal set by the user

        // Iterate through the list of tasks
        for (Task task : TaskList.tasksList) {
            // Check if the task is an instance of GoalTask
            if (task instanceof GoalTask) {
                GoalTask goalTask = (GoalTask) task; // Cast Task to GoalTask
                // Check if the goal is related to calories
                if (goalTask.getDescription().startsWith("c")) {
                    calorieGoal = goalTask.getGoalAmount(); // Retrieve calorie goal
                } else if (goalTask.getDescription().startsWith("w")) {
                    waterGoal = goalTask.getGoalAmount(); // Retrieve water goal
                }
            }
        }

        // Print the goals using GoalsUi
        GoalsUi.printShowGoalsMessage(calorieGoal, waterGoal);
    }
}

