package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to switch between count up timer and count down timer of focus timer.
 */
public class SwitchTimerCommand implements Command {

    public FocusTimer focusTimer;

    /**
     * Constructs a StopTimerCommand with the concerned focus timer.
     *
     * @param timer FocusTimer object to be stopped.
     */
    public SwitchTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    /**
     * Executes the command by switching the mode of the timer.
     *
     * @throws FocusException If the status of focus timer is not currently running.
     */
    @Override
    public void execute() throws FocusException {
        if (focusTimer.getStartStatus()) {
            throw new FocusException("Unable to change as timer is running");
        }
        focusTimer.switchTimer();
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
