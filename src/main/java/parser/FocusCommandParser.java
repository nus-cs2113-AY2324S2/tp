package parser;

import commands.Command;
import commands.focuscommands.SetResumeCommand;
import commands.focuscommands.StartTimerCommand;
import commands.focuscommands.StopTimerCommand;
import commands.focuscommands.SwitchTimerCommand;
import commands.focuscommands.SetPauseCommand;
import commands.focuscommands.CheckTimeCommand;
import commands.focuscommands.SetTimingCommand;

import exceptions.FocusException;
import focus.FocusTimer;

/**
 * FocusCommandParser class determines the type of command based on the user's input and
 * maps it to the corresponding command.
 */
public class FocusCommandParser {
    /**
     * Determines the command based on user input and returns the corresponding command object.
     *
     * @param focusTimer  The focus timer object for wellness360.
     * @param commandArgs The command arguments from user's input.
     * @return Type of command to be executed.
     * @throws FocusException If the type of command is not part of the list of commands.
     */
    public static Command determineFocusCommand(FocusTimer focusTimer, String commandArgs) throws FocusException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFocusTimerCommand = userCommand[0];
        String focusTimerCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch (userFocusTimerCommand) {
        case "start":
            return new StartTimerCommand(focusTimer);
        case "stop":
            return new StopTimerCommand(focusTimer);
        case "switch":
            return new SwitchTimerCommand(focusTimer);
        case "pause":
            return new SetPauseCommand(focusTimer);
        case "resume":
            return new SetResumeCommand(focusTimer);
        case "check":
            return new CheckTimeCommand(focusTimer);
        case "set":
            return new SetTimingCommand(focusTimer, focusTimerCommandArgs);
        default:
            throw new FocusException("Unknown Focus Timer command");
        }
    }
}
