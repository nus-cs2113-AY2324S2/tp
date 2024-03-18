package ActiveEdge.Command;

import ActiveEdge.Task.WaterTask;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.TaskList;

public class LogWaterCommand {
    private String quantityString;

    public LogWaterCommand(String quantityString) {
        this.quantityString = quantityString;
    }

    public void execute() throws ActiveEdgeException {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new ActiveEdgeException("Invalid water quantity. Please provide a valid integer.");
        }

        if (quantity <= 0) {
            System.out.println("Water quantity must be above 0. Please try again.");
        } else {
            WaterTask waterTask = new WaterTask(quantity); // Changed to WaterTask
            TaskList.tasksList.add(waterTask); // Changed to WaterTask
            CommandUi.printWaterLogMessage(waterTask); // Changed to WaterTask
        }
    }
}
