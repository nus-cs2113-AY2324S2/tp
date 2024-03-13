package ActiveEdge.Command;

import ActiveEdge.Task.TaskList;

public abstract class Command {
    public abstract void execute();

    public abstract void execute(TaskList meal);
}
