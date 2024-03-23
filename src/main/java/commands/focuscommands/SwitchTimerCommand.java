package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class SwitchTimerCommand implements Command {

    public FocusTimer focusTimer;

    public SwitchTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }
    @Override
    public void execute() throws FocusException {
        if(focusTimer.getStatus()) {
            throw new FocusException("Unable to change as timer is running");
        }
        focusTimer.switchTimer();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
