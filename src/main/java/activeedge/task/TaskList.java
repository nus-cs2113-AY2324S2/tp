package activeedge.task;
import java.util.ArrayList;

public class TaskList {
    // Static constant ArrayList to store Task objects
    public static final ArrayList<Task> TASKS_LIST = new ArrayList<Task>();

    public static Task delete(int index) {
        if (index >= 0 && index < TASKS_LIST.size()) {
            return TASKS_LIST.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public void add(Task task) {
        TASKS_LIST.add(task);
    }

    public static Task get() {
        int index = 0;
        if (index >= 0 && index < TASKS_LIST.size()) {
            return TASKS_LIST.get(index);
        } else {
            // Handle index out of bounds, you can throw an exception or return null
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public static void clearTasks() {
        TASKS_LIST.clear();
    }

}
