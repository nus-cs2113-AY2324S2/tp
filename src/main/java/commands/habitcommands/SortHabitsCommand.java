package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

public class SortHabitsCommand implements Command {
    private HabitTracker habitTracker;

    public SortHabitsCommand(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    @Override
    public void execute() throws HabitException {
        habitTracker.sortHabits();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
