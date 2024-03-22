package data;

public class Deadline extends Task {
    protected String byDate;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Deadlines are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param by The date of the task's deadline as a string.
     */
    public Deadline(String name, String by) {
        super(name);
        this.byDate = by;
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return D which represents a Deadline task.
     */

    @Override
    public String getTaskType () {
        return "D";
    }

    /**
     * Returns the name of the task that invokes this function.
     * Override function of superclass Task.
     *
     * @return Returns the string of the task's name, including the by date.
     */
    @Override
    public String getName () {
        return name + " (by: " + byDate + ")";
    }

}
