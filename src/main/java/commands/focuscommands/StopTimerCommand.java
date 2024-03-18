package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class StopTimerCommand implements Command {
    public FocusTimer focusTimer;
    public StopTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }
    @Override
    public void execute() throws FocusException {
        if (!focusTimer.getStatus()) {
            throw new FocusException("Error! Clock is not running...");
        }
        focusTimer.setStopTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
