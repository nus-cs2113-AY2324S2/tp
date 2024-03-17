package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

public class UpdateHabitCountCommand implements Command {
    private HabitTracker habitTracker;
    private String habitID;
    private String updatedCount;

    public UpdateHabitCountCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;

        String[] parts = habitCommandArgs.trim().split("/id | /by");
        if (!(parts.length == 3)) {
            throw new HabitException("Incorrect update command formatting\n" +
                    "Use Format: habit update /id <habit_ID> /by <increment_count>\n" +
                    "Note: for <increment_count>, use '+1' to increase by 1, '-1' to decrease by 1");
        }

        this.habitID = parts[1].trim();
        this.updatedCount = parts[2].trim();
    }

    public void execute() throws HabitException {
        habitTracker.updateHabitCount(habitID, updatedCount);
    }

    public boolean isExit() {
        return false;
    }

}

