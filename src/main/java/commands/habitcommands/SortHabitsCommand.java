package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents a command to sort the habits according to their priority.
 */
public class SortHabitsCommand implements Command {
    private HabitTracker habitTracker;

    /**
     * Constructs a SortHabitsCommand object.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     */
    public SortHabitsCommand(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    /**
     * Execute the command to sort the habits according to their priority.
     *
     * @throws HabitException If there are any formatting issues.
     */
    @Override
    public void execute() throws HabitException {
        habitTracker.sortHabits();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
