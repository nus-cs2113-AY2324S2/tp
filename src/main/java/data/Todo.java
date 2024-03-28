package data;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;

import java.util.GregorianCalendar;

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

    /**
     * Method that creates the save format for a Todo task.
     * Overrides super dummy function.
     *
     * @return The String representation of the save format for this task.
     */
    @Override
    public String getSaveFormat () {
        return getTaskType() + "|" + getName();
    }

    public VEvent todoToVEvent(Todo todo) {
        java.util.Calendar startDate = new GregorianCalendar();
        startDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        startDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        startDate.set(java.util.Calendar.YEAR, 2023);
        startDate.set(java.util.Calendar.HOUR_OF_DAY, 9);
        startDate.set(java.util.Calendar.MINUTE, 0);
        startDate.set(java.util.Calendar.SECOND, 0);

        // End Date is on: April 1, 2023, 13:00
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        endDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        endDate.set(java.util.Calendar.YEAR, 2023);
        endDate.set(java.util.Calendar.HOUR_OF_DAY, 13);
        endDate.set(java.util.Calendar.MINUTE, 0);
        endDate.set(java.util.Calendar.SECOND, 0);

        // Create the event
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, "Team Meeting");

        return meeting;
    }
}
