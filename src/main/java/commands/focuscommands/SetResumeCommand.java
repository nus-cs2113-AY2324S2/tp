package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class SetResumeCommand implements Command {
    public FocusTimer focusTimer;

    public SetResumeCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    public boolean timerStartedAndPaused() {
        return (focusTimer.getStartStatus() && focusTimer.getPausedStatus());
    }

    public void execute() throws FocusException {
        if (!timerStartedAndPaused()) {
            throw new FocusException("Timer is already resumed or Timer hasn't started..");
        }
        focusTimer.setResumeTiming();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
