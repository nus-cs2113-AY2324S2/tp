package command;

import activeedge.task.GoalTask;
import activeedge.task.TaskList;
import activeedge.ui.CommandUi;
import activeedge.task.Task;
import activeedge.task.WaterTask;

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
