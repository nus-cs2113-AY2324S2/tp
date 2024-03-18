package ActiveEdge.Command;

import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.WaterTask;

import java.util.ArrayList;

public class ViewWaterIntakeCommand {
    public void execute() {
        int totalWaterIntake = getTotalWaterIntake(TaskList.tasksList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake);
    }

    private int getTotalWaterIntake(ArrayList<Task> tasksList) {
        int totalWaterIntake = 0;
        for (Task task : tasksList) {
            if (task instanceof WaterTask) {
                totalWaterIntake += ((WaterTask) task).getQuantity();
            }
        }
        return totalWaterIntake;
    }
}
