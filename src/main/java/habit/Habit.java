package habit;

import exceptions.HabitException;

public class Habit {
    private String description;
    private int habitCount;

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
