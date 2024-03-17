package parser;

import commands.Command;
import exceptions.FocusException;

public class FocusCommandParser {
    public static Command determineFocusCommand(String commandArgs) throws FocusException {
        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFocusTimerCommand = userCommand[0];
        String focusTimerCommandArgs = userCommand.length == 2 ? userCommand[1] : "";

        switch (userFocusTimerCommand) {
        case "start":
            // To be added
        case "stop":
            // To be added
        default:
            throw new FocusException("Unknown Focus Timer command");
        }
    }
}
