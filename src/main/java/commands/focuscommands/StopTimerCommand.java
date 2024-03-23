package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to stop the timer for focus timer object.
 */
public class StopTimerCommand implements Command {
    public FocusTimer focusTimer;

    /**
     * Constructs a StopTimerCommand with the concerned focus timer.
     * @param timer FocusTimer object to be stopped.
     */
    public StopTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    /**
     * Executes the command bye setting the stop timing of the timer.
     * @throws FocusException If the status of focus timer is not currently running.
     */
    @Override
    public void execute() throws FocusException {
        if (!focusTimer.getStatus()) {
            throw new FocusException("Error! Clock is not running...");
        }
        focusTimer.setStopTiming();
    }

    /**
     * Determines whether the command is an exit message.
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
