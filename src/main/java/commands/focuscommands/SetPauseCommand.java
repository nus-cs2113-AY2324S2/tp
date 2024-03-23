package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class SetPauseCommand implements Command {
    public FocusTimer focusTimer;

    public SetPauseCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    @Override
    public void execute() throws FocusException {
        if (focusTimer.getPausedStatus() || !focusTimer.getStatus()) {
            throw new FocusException("Timer is already paused or Timer hasn't started.");
        }
        focusTimer.setPauseTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
