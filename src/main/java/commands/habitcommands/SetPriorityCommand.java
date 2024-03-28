package commands.habitcommands;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents a command to set the priority of a habit.
 */
public class SetPriorityCommand implements Command {
    private static final int REQUIRED_PARAMETERS = 3;
    private HabitTracker habitTracker;
    private String priority;
    private int habitID;

    /**
     * Constructs a SetPriorityCommand object with user input
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     * @param habitCommandArgs User input for the set priority command.
     * @throws HabitException If there are any formatting issues, or if there is an
     *                        invalid habit ID or priority level
     */
    public SetPriorityCommand(HabitTracker habitTracker, String habitCommandArgs) throws HabitException {
        this.habitTracker = habitTracker;

        String[] parts = habitCommandArgs.trim().split("/id | /priority");

        if (!(parts.length == REQUIRED_PARAMETERS)) {
            throw new HabitException("Incorrect set priority command formatting\n" +
                    "Use Format: habit set /id <habit_ID> /priority <priority_level>\n" +
                    "Note: for <priority_level>, there are 3 levels --> low, med, high");
        }

        try {
            this.habitID = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        String priorityLevel = parts[2].trim().toLowerCase();
        if (!priorityLevel.equals("high") && !priorityLevel.equals("med") && !priorityLevel.equals("low")) {
            throw new HabitException("Invalid priority level!");
        }

        this.priority = priorityLevel;
    }

    /**
     * Execute the command to set priority of a habit.
     *
     * @throws HabitException If there are any formatting issues.
     */
    @Override
    public void execute() throws HabitException {
        habitTracker.setPriorityLevel(habitID, priority);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
