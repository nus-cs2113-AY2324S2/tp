package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class CheckTimeCommand implements Command {
    FocusTimer focustimer;

    public CheckTimeCommand(FocusTimer timer) {
        this.focustimer = timer;
    }

    @Override
    public void execute() throws FocusException {
        if(!focustimer.getStartStatus()) {
            throw new FocusException("Timer have not started. Please use focus start.");
        }
        focustimer.checkTime();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
