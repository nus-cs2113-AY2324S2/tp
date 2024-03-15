package ActiveEdge.Command;

import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.WaterTracker;

public class LogWaterCommand extends Command {
    private int quantity;

    public LogWaterCommand(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException {
        WaterTracker waterTracker = new WaterTracker("Water intake", quantity);
        tasks.add(waterTracker);
        System.out.println("Successfully logged " + quantity + " ml of water.");
    }
}