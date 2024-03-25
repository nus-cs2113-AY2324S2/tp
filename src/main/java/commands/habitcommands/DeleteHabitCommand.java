package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents a command to add new habits.
 */
public class DeleteHabitCommand implements Command{

    private HabitTracker habitTracker;
    private int habitID;

    /**
     * Constructs a DeleteHabitCommand object with user input.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     * @param habitCommandArgs User input for the delete habit command.
     * @throws HabitException If there are any formatting issues.
     */
    public DeleteHabitCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;

        if (habitCommandArgs.isEmpty()) {
            throw new HabitException("Please provide a valid habit ID.\n" +
                    "Use Format: habit delete <habit_ID>");
        }

        try {
            this.habitID = Integer.parseInt(habitCommandArgs.trim());
        } catch (NumberFormatException e) {
            throw new HabitException("Please provide a valid habit ID.");
        }
    }

    /**
     * Execute the command to delete a habit from the habit tracker.
     *
     * @throws HabitException If there are any formatting issues.
     */
    @Override
    public void execute() throws HabitException {
        habitTracker.deleteHabit(habitID);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
