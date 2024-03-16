package ActiveEdge.Command;
import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.TaskList;

public class LogWaterCommand {
    private String quantityString;

    public LogWaterCommand(String quantityString) {
        this.quantityString = quantityString;
    }

    public void execute() throws ActiveEdgeException {
        try {
            int quantity = Integer.parseInt(quantityString);
            if (quantity <= 0) {
                throw new ActiveEdgeException("Water quantity must be a positive integer.");
            }
            LogWaterTask logWaterTask = new LogWaterTask(quantity);
            TaskList.tasksList.add(logWaterTask);
            CommandUi.printWaterLogMessage(logWaterTask);
        } catch (NumberFormatException e) {
            throw new ActiveEdgeException("Invalid water quantity. Please provide a valid integer.");
        }
    }
}


