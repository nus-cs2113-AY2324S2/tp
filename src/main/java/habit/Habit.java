package habit;

import exceptions.HabitException;

/**
 * Represents a Habit.
 */
public class Habit {
    private String description;
    private int habitCount;

    /**
     * Constructs a habit object with the habit description.
     *
     * @param description The habit description to be added.
     */
    public Habit(String description) {
        this.description = description;
        this.habitCount = 0;
    }

    public String getDescription() {
        return description;
    }

    public int getHabitCount() {
        return habitCount;
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

    public String toString() {
        return description + " [count: " + habitCount + "]";
    }

}
