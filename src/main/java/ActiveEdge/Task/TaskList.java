package ActiveEdge.Task;
import java.util.ArrayList;

public class TaskList {
    // Static constant ArrayList to store Task objects
    public static final ArrayList<Task> tasksList = new ArrayList<Task>();

    public Task getTask(int index) { return tasksList.get(index); }

    public void addTask(Task task) { tasksList.add(task); }

}
