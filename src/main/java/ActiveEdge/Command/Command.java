package ActiveEdge.Command;

import ActiveEdge.Storage;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException;
}
