package data;

public class Event extends Task {
    protected String startDate;
    protected String endDate;
    private final String startTime;
    private final String endTime;


    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Events are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param start The starting time/date of the task.
     * @param end The ending time/date of the task.
     */
    public Event(String name, String start, String end, String startTime, String endTime) {
        super(name);
        this.startDate = start;
        this.endDate = end;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter for start date of Event task.
     * Overrides super dummy function.
     *
     * @return The String representation of the start date.
     */
    @Override
    public String getStartDate() {
        return startDate;
    }

    /**
     * Getter for start time of Event task.
     * Overrides super dummy function.
     *
     * @return The String representation of the start time.
     */
    @Override
    public String getStartTime() {
        return startTime;
    }

    /**
     * Getter for end time of Event task.
     * Overrides super dummy function.
     *
     * @return The String representation of the end time.
     */
    @Override
    public String getEndTime() {
        return endTime;
    }

    /**
     * Getter for end date of Event task.
     * Overrides super dummy function.
     *
     * @return The String representation of the end date.
     */
    @Override
    public String getEndDate() {
        return endDate;
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
     * Method that creates the save format for an Event task.
     * Overrides super dummy function.
     *
     * @return The String representation of the save format for this task.
     */
    @Override
    public String getSaveFormat () {
        return getTaskType() + "|" + getName() + "|" + getStartDate() + "|" + getEndDate() + "|" + getStartTime() + "|"
                + getEndTime();
    }
}

