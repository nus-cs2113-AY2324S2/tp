package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.Habit;
import habit.HabitTracker;

/**
 * A command implementation for adding new habits.
 */
public class AddHabitCommand implements Command {
    private HabitTracker habitTracker;
    private Habit newHabit;

    /**
     * Constructs a AddHabitCommand object with user input.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     * @param habitCommandArgs User input for the add habit command.
     * @throws HabitException If there are any formatting issues.
     */
    public AddHabitCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;
        if (habitCommandArgs.isEmpty()) {
            throw new HabitException("Habit Description cannot be left empty.");
        }
        this.newHabit = new Habit(habitCommandArgs.trim());
    }

    /**
     * Execute the command to add a new habit into the habit tracker.
     *
     * @throws HabitException If there are any formatting issues
     */
    @Override
    public void execute() throws HabitException {
        habitTracker.addHabit(newHabit);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

