package parser;

import commands.Command;
import commands.focuscommands.StartTimerCommand;
import commands.focuscommands.StopTimerCommand;
import exceptions.FocusException;
import focus.FocusTimer;

public class FocusCommandParser {
    public static Command determineFocusCommand(FocusTimer focusTimer, String commandArgs) throws FocusException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFocusTimerCommand = userCommand[0];
        String focusTimerCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch (userFocusTimerCommand) {
        case "start":
            return new StartTimerCommand(focusTimer);
        case "stop":
            return new StopTimerCommand(focusTimer);
        default:
            throw new FocusException("Unknown Focus Timer command");
        }
    }
}
