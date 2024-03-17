package seedu.duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() { this.tasks = new ArrayList<>();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public void deleteTask(int index){
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
