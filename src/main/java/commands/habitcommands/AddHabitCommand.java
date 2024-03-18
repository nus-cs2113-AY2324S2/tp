package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.Habit;
import habit.HabitTracker;

public class AddHabitCommand implements Command {
    private HabitTracker habitTracker;
    private Habit newHabit;

    public AddHabitCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;
        if (habitCommandArgs.isEmpty()) {
            throw new HabitException("Habit Description cannot be left empty.");
        }
        this.newHabit = new Habit(habitCommandArgs.trim());
    }

    public void execute() throws HabitException {
        habitTracker.addHabit(newHabit);
    }

    public boolean isExit() {
        return false;
    }
}

