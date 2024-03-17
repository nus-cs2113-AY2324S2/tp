package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class StopTimerCommand implements Command {
    @Override
    public void execute() throws FocusException {
        if (!FocusTimer.isStarted) {
            throw new FocusException("Error! Clock is not running...");
        }
        FocusTimer.setStopTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
