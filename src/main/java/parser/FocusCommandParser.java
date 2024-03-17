package parser;

import commands.Command;
import commands.focuscommands.StartTimerCommand;
import commands.focuscommands.StopTimerCommand;
import exceptions.FocusException;

public class FocusCommandParser {
    public static Command determineFocusCommand(String commandArgs) throws FocusException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFocusTimerCommand = userCommand[0];
        String focusTimerCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch (userFocusTimerCommand) {
        case "start":
            return new StartTimerCommand();
        case "stop":
            return new StopTimerCommand();
        default:
            throw new FocusException("Unknown Focus Timer command");
        }
    }
}
