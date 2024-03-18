package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to start the timer for focus timer object.
 */
public class StartTimerCommand implements Command {
    public FocusTimer focusTimer;

    /**
     * Constructs a StartTimerCommand with the concerned focus timer.
     * @param timer FocusTimer object to be started.
     */
    public StartTimerCommand(FocusTimer timer) {
        this.focusTimer = timer;
    }

    /**
     * Executes the command bye setting the start timing of the timer.
     * @throws FocusException If the status of focus timer is already running.
     */
    @Override
    public void execute() throws FocusException {
        if (focusTimer.getStatus()) {
            throw new FocusException("Error! Clock has already started.");
        }
        focusTimer.setStartTiming();
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
