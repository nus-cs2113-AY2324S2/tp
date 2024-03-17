package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class StartTimerCommand implements Command {
    @Override
    public void execute() throws FocusException {
        if (FocusTimer.isStarted) {
            throw new FocusException("Error! Clock has already started.");
        }
        FocusTimer.setStartTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
