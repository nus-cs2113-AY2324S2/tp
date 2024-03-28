package command;

import activeedge.task.WaterTask;
import activeedge.ui.CommandUi;
import activeedge.task.TaskList;
import java.time.LocalDateTime;


/**
 * Represents a command to log water intake.
 */
public class LogWaterCommand {
    private String quantityString;
    private LocalDateTime dateTime;

    /**
     * Constructs a LogWaterCommand object with the specified quantity string.
     *
     * @param quantityString The string representing the quantity of water to log.
     */
    public LogWaterCommand(String quantityString, LocalDateTime dateTime) {
        this.quantityString = quantityString;
        this.dateTime = dateTime;
    }

    /**
     * Executes the command to log water intake.
     *
     * @throws ActiveEdgeException If an error occurs during execution.
     */
    public void execute() throws ActiveEdgeException {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new ActiveEdgeException("Invalid water quantity. Please provide a valid integer.");
        }

        assert quantity > 0 : "Water quantity must be above 0";

        if (quantity <= 0) {
            System.out.println("Water quantity must be above 0. Please try again.");
        } else {
            WaterTask waterTask = new WaterTask(quantity, dateTime);
            TaskList.tasksList.add(waterTask);
            CommandUi.printWaterLogMessage(waterTask);
        }
    }
}
