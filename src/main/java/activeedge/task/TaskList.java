package activeedge.task;
import java.util.ArrayList;

public class TaskList {
    // Static constant ArrayList to store Task objects
    public static final ArrayList<Task> tasksList = new ArrayList<Task>();

    public static Task delete(int index) {
        if (index >= 0 && index < tasksList.size()) {
            return tasksList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public void add(Task task) {
        tasksList.add(task);
    }

    public static Task get() {
        int index = 0;
        if (index >= 0 && index < tasksList.size()) {
            return tasksList.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public static void clearTasks() {
        tasksList.clear();
    }

}
