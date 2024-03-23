package commands.habitcommands;

import commands.Command;
import habit.HabitTracker;

public class ListHabitsCommand implements Command {
    private HabitTracker habitTracker;

    /**
     * Constructs a ListHabitsCommand object.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     */
    public ListHabitsCommand(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    /**
     * Execute the command to list out all the habits in the habit tracker.
     */
    @Override
    public void execute() {
        habitTracker.listHabits();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
