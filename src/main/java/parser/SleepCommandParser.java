package parser;

import commands.Command;
import commands.sleepcommands.AddSleepCommand;
import commands.sleepcommands.ListSleepCommand;
import exceptions.SleepException;
import sleep.SleepTracker;

/**
 * Represents the input parser of Sleep Tracker.
 * Parses user input into Sleep Tracker commands and details of the command.
 * Generates the respective Command objects based on the parsed command keyword.
 */
public class SleepCommandParser {
    /**
     * Parses user input into command and details, and creates the corresponding Command object.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param commandArgs User input for the sleep tracker command.
     * @return Command object to be executed.
     * @throws SleepException If the user input is not part of the command list.
     */
    public static Command determineSleepCommand(SleepTracker sleepTracker, String commandArgs)
            throws SleepException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userSleepCommand = userCommand[0];

        String sleepCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch(userSleepCommand) {
        case "add":
            return new AddSleepCommand(sleepTracker, sleepCommandArgs);
        case "list":
            return new ListSleepCommand(sleepTracker, sleepCommandArgs);
        default:
            throw new SleepException("Unknown sleep command");
        }
    }
}
