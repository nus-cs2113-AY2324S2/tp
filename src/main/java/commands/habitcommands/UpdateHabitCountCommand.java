package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents a command to update a habit count.
 */
public class UpdateHabitCountCommand implements Command {
    private static final int REQUIRED_PARAMETERS = 3;
    private HabitTracker habitTracker;
    private int habitID;
    private String updatedCount;

    /**
     * Constructs a UpdateHabitCountCommand object with user input.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     * @param habitCommandArgs User input for the update habit count command.
     * @throws HabitException If there are any formatting issues.
     */
    public UpdateHabitCountCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;

        String[] parts = habitCommandArgs.trim().split("/id | /by");

        if (parts.length != REQUIRED_PARAMETERS) {
            throw new HabitException("Incorrect update command formatting\n" +
                    "Use Format: habit update /id <habit_ID> /by <increment_count>\n" +
                    "Note: for <increment_count>, use '+1' to increase by 1, '-1' to decrease by 1");
        }

        try {
            this.habitID = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        this.updatedCount = parts[2].trim();
    }

    /**
     * Execute the command to update the habit count of a habit.
     */
    @Override
    public void execute() throws HabitException {
        habitTracker.updateHabitCount(habitID, updatedCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

