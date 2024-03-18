package Command;

import ActiveEdge.Task.GoalTask;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.WaterTask;

import java.util.ArrayList;

public class ViewWaterIntakeCommand {
    public void execute() {
        int totalWaterIntake = getTotalWaterIntake(TaskList.tasksList);
        int waterGoal = getWaterGoal(TaskList.tasksList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake, waterGoal);

    }

    int getTotalWaterIntake(ArrayList<Task> tasksList) {
        int totalWaterIntake = 0;
        for (Task task : tasksList) {
            if (task instanceof WaterTask) {
                totalWaterIntake += ((WaterTask) task).getQuantity();
            }
        }
        return totalWaterIntake;
    }

    public int getWaterGoal(ArrayList<Task> tasksList) {
        for (Task task : tasksList) {
            if (task instanceof GoalTask && task.getDescription().startsWith("w")) {
                return ((GoalTask) task).getGoalAmount();
            }
        }
        return 0; // Default to 0 if no water goal is found
    }

}
