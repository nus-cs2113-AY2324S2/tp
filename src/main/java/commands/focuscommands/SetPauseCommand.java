package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to pause the current timer for focus timer object.
 */
public class SetPauseCommand implements Command {
    public FocusTimer focusTimer;

    /**
     * Constructs a StopTimerCommand with the concerned focus timer.
     *
     * @param timer FocusTimer object to be stopped.
     */
    public SetPauseCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    /**
     * Checks whether the timer is currently running or is currently being paused.
     *
     * @return true if timer is currently paused or hasn't started. False otherwise.
     */
    public boolean timerNotStartedOrPaused() {
        return (focusTimer.getPausedStatus() || !focusTimer.getStartStatus());
    }

    /**
     * Executes the command by pausing the timing of the timer.
     *
     * @throws FocusException If the status of focus timer is not currently running or is in paused state.
     */
    @Override
    public void execute() throws FocusException {
        if (timerNotStartedOrPaused()) {
            throw new FocusException("Timer is already paused or Timer hasn't started.");
        }
        focusTimer.setPauseTiming();
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
