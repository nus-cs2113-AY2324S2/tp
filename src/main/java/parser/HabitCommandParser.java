package parser;

import commands.Command;
import exceptions.HabitException;
import habit.HabitTracker;

public class HabitCommandParser {
    public static Command determineHabitCommand(HabitTracker habitTracker, String commandArgs) throws HabitException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userHabitCommand = userCommand[0].trim();

        String habitCommandArgs = userCommand.length == 2 ? userCommand[1].trim() : "";

        switch(userHabitCommand) {
        case "add":
        case "list":
        case "update":
        default:
            throw new HabitException("Unknown command");
        }
    }
}
