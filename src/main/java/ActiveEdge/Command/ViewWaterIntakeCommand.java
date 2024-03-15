package ActiveEdge.Command;

import ActiveEdge.Task.Task;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Task.WaterTracker;
import ActiveEdge.Ui.CommandUi;

public class ViewWaterIntakeCommand extends Command {
    @Override
    public void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException {
        int totalWaterIntake = 0;
        for (int i = 0; i < tasks.tasksList.size(); i++) {
            Task task = tasks.tasksList.get(i);
            if (task instanceof WaterTracker) {
                totalWaterIntake += ((WaterTracker) task).getQuantity();
            }
        }

        System.out.println("Total water intake: " + totalWaterIntake + " ml");
    }
}
