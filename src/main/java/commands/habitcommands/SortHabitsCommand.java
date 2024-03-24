package commands.habitcommands;

import commands.Command;
import habit.HabitTracker;

public class SortHabitsCommand implements Command {
    private HabitTracker habitTracker;

    public SortHabitsCommand(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    @Override
    public void execute() {
        habitTracker.sortHabits();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
