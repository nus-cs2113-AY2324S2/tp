package habit;

import exceptions.HabitException;

public class Habit {
    protected String description;
    protected int habitCount;

    public Habit(String description) {
        this.description = description;
        this.habitCount = 0;
    }

    public void updateCount(String updatedCount) throws HabitException {

    }

    public String toString() {
        return description + " [count: " + habitCount + "]";
    }

}
