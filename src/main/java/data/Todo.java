package data;

public class Todo extends Task {

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Todos are also considered as tasks.
     *
     * @param name The name of the task to be created.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return T which represents a Todo task.
     */

    @Override
    public String getTaskType () {
        return "T";
    }
}
