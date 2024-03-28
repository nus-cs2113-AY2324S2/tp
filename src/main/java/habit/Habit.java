package habit;

import exceptions.HabitException;

/**
 * Represents a Habit.
 */
public class Habit {
    private String description;
    private int habitCount;
    private Priority priority;

    /**
     * Constructs a habit object with the habit description.
     *
     * @param description The habit description to be added.
     */
    public Habit(String description) {
        this.description = description;
        this.habitCount = 0;
        this.priority = Priority.LOW;
    }

    public Habit(String description, int habitCount, Priority priority) {
        this.description = description;
        this.habitCount = habitCount;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public int getHabitCount() {
        return habitCount;
    }

    public Priority getPriority() {
        return priority;
    }

    /**
     * Updates the habit count of a habit.
     *
     * @param updatedCount The count to be added to the existing habit count.
     * @return An integer type of the change in count to be added.
     * @throws HabitException If the user tries to decrease a habit count to below zero, or use a non-numerical number.
     */
    public int updateCount(String updatedCount) throws HabitException {
        int changeInCount = 0;

        try {
            changeInCount = Integer.parseInt(updatedCount);
            if (habitCount + changeInCount < 0) {
                throw new HabitException("You cannot decrement a habit count to below zero");
            }

            habitCount += changeInCount;

        } catch (NumberFormatException e) {
            throw new HabitException("Please enter a valid count\n" +
                    "Use: '+1' to increase count, '-1' to decrease count ");
        }

        return changeInCount;
    }

    /**
     * Sets the priority of a habit.
     *
     * @param priority The priority level of a habit from user input.
     */
    public void setPriority (String priority) {
        switch (priority) {
        case "low":
            this.priority = Priority.LOW;
            break;
        case "med":
            this.priority = Priority.MED;
            break;
        case "high":
            this.priority = Priority.HIGH;
            break;
        default:
        }
    }

    public String toString() {
        return " [" + priority + "] " + description + " [count: " + habitCount + "]";
    }

}
