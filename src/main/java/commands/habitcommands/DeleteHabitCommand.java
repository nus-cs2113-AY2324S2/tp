package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

public class DeleteHabitCommand implements Command{

    private HabitTracker habitTracker;
    private int habitID;

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

    @Override
    public void execute() throws HabitException {
        habitTracker.deleteHabit(habitID);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
