package ActiveEdge.Command;

import ActiveEdge.Storage;
import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.ViewWaterTask;

public class ViewWaterIntakeCommand {
    public void execute() {
        int totalWaterIntake = ViewWaterTask.getTotalWaterIntake(tasksList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake);
    }
}
