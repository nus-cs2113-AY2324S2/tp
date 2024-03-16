package ActiveEdge.Command;

import ActiveEdge.Storage;
import ActiveEdge.Task.LogWaterTask;
import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Ui.CommandUi;

public class LogWaterCommand{
    private int quantity;

    public LogWaterCommand(int quantity) {
        this.quantity = quantity;
    }

    public void execute() throws ActiveEdgeException {
        if (quantity <= 0) {
            throw new ActiveEdgeException("Invalid water quantity. Please enter a positive value.");
        }
        LogWaterTask logWaterTask = new LogWaterTask(quantity);
        tasksList.add(logWaterTask);
        CommandUi.printWaterLogMessage(logWaterTask);
    }
}
