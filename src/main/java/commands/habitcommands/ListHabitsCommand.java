package commands.habitcommands;

import commands.Command;
import habit.HabitTracker;

public class ListHabitsCommand implements Command {
    private HabitTracker habitTracker;

    public ListHabitsCommand(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    public void execute() {
        habitTracker.listHabits();
    }

    public boolean isExit() {
        return false;
    }
}
