package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to resume the current timer for focus timer object.
 */
public class SetResumeCommand implements Command {
    public FocusTimer focusTimer;

    /**
     * Constructs a StopTimerCommand with the concerned focus timer.
     *
     * @param timer FocusTimer object to be stopped.
     */
    public SetResumeCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    /**
     * Checks whether the timer is currently running or is currently being paused.
     *
     * @return true if timer has been started and is currently in the paused state.
     */
    public boolean timerStartedAndPaused() {
        return (focusTimer.getStartStatus() && focusTimer.getPausedStatus());
    }

    /**
     * Executes the command by resuming the timing of the timer.
     *
     * @throws FocusException If the status of focus timer is not currently running or is not in paused state.
     */
    public void execute() throws FocusException {
        if (!timerStartedAndPaused()) {
            throw new FocusException("Timer is already resumed or Timer hasn't started..");
        }
        focusTimer.setResumeTiming();
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
