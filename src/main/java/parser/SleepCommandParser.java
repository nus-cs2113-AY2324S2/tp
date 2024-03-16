package parser;

import commands.Command;
import commands.sleepcommands.AddSleepCommand;
import commands.sleepcommands.ListSleepCommand;
import exceptions.SleepException;
import sleep.SleepTracker;

public class SleepCommandParser {
    public static Command determineSleepCommand(SleepTracker sleepTracker, String commandArgs)
            throws SleepException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userSleepCommand = userCommand[0];

        String sleepCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch(userSleepCommand) {
        case "add":
            return new AddSleepCommand(sleepTracker, sleepCommandArgs);
        case "list":
            return new ListSleepCommand(sleepTracker);
        default:
            throw new SleepException("Unknown sleep command");
        }
    }
}
