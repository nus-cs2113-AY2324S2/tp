package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class StartTimerCommand implements Command {
    public FocusTimer focusTimer;
    public StartTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }
    @Override
    public void execute() throws FocusException {
        if (focusTimer.getStatus()) {
            throw new FocusException("Error! Clock has already started.");
        }
        focusTimer.setStartTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
