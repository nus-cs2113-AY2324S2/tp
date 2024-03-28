package parser;

import commands.Command;
import commands.habitcommands.AddHabitCommand;
import commands.habitcommands.DeleteHabitCommand;
import commands.habitcommands.HabitHelpCommand;
import commands.habitcommands.ListHabitsCommand;
import commands.habitcommands.SetPriorityCommand;
import commands.habitcommands.SortHabitsCommand;
import commands.habitcommands.UpdateHabitCountCommand;
import exceptions.HabitException;
import habit.HabitTracker;

/**
 * Represents the input parser of Habit Tracker.
 * Parses user input into Habit Tracker commands.
 * Generates the respective Command objects to be executed, based on the parsed command keyword.
 */
public class HabitCommandParser {
    private static final int COMMAND_LENGTH = 2;

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

        String habitCommandArgs = userCommand.length == COMMAND_LENGTH ? userCommand[1].trim() : "";

        switch(userHabitCommand) {
        case "add":
            return new AddHabitCommand(habitTracker, habitCommandArgs);
        case "list":
            return new ListHabitsCommand(habitTracker);
        case "update":
            return new UpdateHabitCountCommand(habitTracker, habitCommandArgs);
        case "delete":
            return new DeleteHabitCommand(habitTracker, habitCommandArgs);
        case "set":
            return new SetPriorityCommand(habitTracker, habitCommandArgs);
        case "sort":
            return new SortHabitsCommand(habitTracker);
        case "help":
            return new HabitHelpCommand();
        default:
            throw new HabitException("Unknown command");
        }
    }
}
