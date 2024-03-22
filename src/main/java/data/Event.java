package data;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Events are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param start The starting time/date of the task.
     * @param end The ending time/date of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return E which represents an Event task.
     */

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the name of the task that invokes this function.
     * Override function of superclass Task.
     *
     * @return Returns the string of the task's name, including the start and end date.
     */

    @Override
    public String getName () {
        return name + "(from: " + startDate + " to: " + endDate + ")";
    }
}

