package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to check the current time for focus timer object.
 */
public class CheckTimeCommand implements Command {
    FocusTimer focustimer;

    /**
     * Constructs a StopTimerCommand with the concerned focus timer.
     *
     * @param timer FocusTimer object to be stopped.
     */
    public CheckTimeCommand(FocusTimer timer) {
        this.focustimer = timer;
    }

    /**
     * Executes the command by checking the current timing of the timer.
     *
     * @throws FocusException If the status of focus timer is not currently running.
     */
    @Override
    public void execute() throws FocusException {
        if(!focustimer.getStartStatus()) {
            throw new FocusException("Timer have not started. Please use focus start.");
        }
        focustimer.checkTime();
    }

    /**
     * Determines whether the command is an exit message.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
