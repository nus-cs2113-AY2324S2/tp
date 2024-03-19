package parser;

import commands.Command;
import commands.habitcommands.AddHabitCommand;
import commands.habitcommands.ListHabitsCommand;
import commands.habitcommands.UpdateHabitCountCommand;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents the input parser of Habit Tracker.
 * Parses user input into Habit Tracker commands.
 * Generates the respective Command objects to be executed, based on the parsed command keyword.
 */
public class HabitCommandParser {
    /**
     * Parses user input into command and details, and creates the corresponding habit command objects.
     *
     * @param habitTracker The HabitTracker instance to be used for storing the habits.
     * @param commandArgs User input for the habit tracker command.
     * @return Command object to be executed.
     * @throws HabitException If the user input is not part of the command list.
     */
    public static Command determineHabitCommand(HabitTracker habitTracker, String commandArgs) throws HabitException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userHabitCommand = userCommand[0].trim();

        String habitCommandArgs = userCommand.length == 2 ? userCommand[1].trim() : "";

        switch(userHabitCommand) {
        case "add":
            return new AddHabitCommand(habitTracker, habitCommandArgs);
        case "list":
            return new ListHabitsCommand(habitTracker);
        case "update":
            return new UpdateHabitCountCommand(habitTracker, habitCommandArgs);
        default:
            throw new HabitException("Unknown command");
        }
    }
}
