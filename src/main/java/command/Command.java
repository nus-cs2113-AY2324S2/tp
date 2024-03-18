package command;

import activeedge.Storage;
import activeedge.task.TaskList;
import activeedge.ui.CommandUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, CommandUi ui, Storage storage) throws ActiveEdgeException;
}
