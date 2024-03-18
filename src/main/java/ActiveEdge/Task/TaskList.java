package ActiveEdge.Task;
import java.util.ArrayList;

public class TaskList {
    // Static constant ArrayList to store Task objects
    public static final ArrayList<Task> tasksList = new ArrayList<Task>();

    public void add(Task task) {
        tasksList.add(task);
    }

    public static long get() {
        if (index >= 0 && index < tasksList.size()) {
            return tasksList.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

}
