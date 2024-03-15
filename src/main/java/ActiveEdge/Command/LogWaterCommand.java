package ActiveEdge.Command;

import ActiveEdge.Storage;
import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;

public class LogWaterCommand extends Command {
    private int quantity;

    public LogWaterCommand(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException {
        if (quantity <= 0) {
            throw new ActiveEdgeException("Invalid water quantity. Please enter a positive value.");
        }

        LogWaterTask logWaterTask = new LogWaterTask(quantity);
        tasks.addTask(logWaterTask);
        CommandUi.printWaterLogMessage(logWaterTask);

    }

    @Override
    public void execute() {
        // Empty implementation for the execute method inherited from Command
    }

    @Override
    public void execute(TaskList meal) {
        // Empty implementation for the execute method inherited from Command
    }
}
