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
     * Getter for by date of Deadline task.
     * Overrides super dummy function.
     *
     * @return The String representation of the by date.
     */
    @Override
    public String getByDate() {
        return byDate;
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
     * Method that creates the save format for a Deadline task.
     * Overrides super dummy function.
     *
     * @return The String representation of the save format for this task.
     */
    @Override
    public String getSaveFormat () {
        return getTaskType() + "|" + getName() + "|" + getByDate();
    }
}
