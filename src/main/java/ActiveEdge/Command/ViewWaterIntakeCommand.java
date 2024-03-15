package ActiveEdge.Command;

import ActiveEdge.Storage;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.ViewWaterTask;

public class ViewWaterIntakeCommand extends Command {
    @Override
    public void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException {
        int totalWaterIntake = ViewWaterTask.getTotalWaterIntake(tasks.tasksList);
        CommandUi.printWaterIntakeMessage(totalWaterIntake);
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
