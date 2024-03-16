package ActiveEdge.Command;

import ActiveEdge.Task.GoalTask;
import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Ui.GoalsUi;

public class AddGoalsCommand  {
    private String description;
    private int goalAmount;

    public AddGoalsCommand(String description, int goalAmount) {
        this.description = description;
        this.goalAmount = goalAmount;
    }

    public void execute() {
        GoalTask addGoalTask = new GoalTask(description, goalAmount);
        tasksList.add(addGoalTask);
        GoalsUi.printAddGoalMessage(description, goalAmount);
    }
}
