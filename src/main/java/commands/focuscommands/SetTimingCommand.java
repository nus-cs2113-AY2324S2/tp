package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

/**
 * Represents the command to set duration for focus timer object countdown timer.
 */
public class SetTimingCommand implements Command {
    private FocusTimer focusTimer;
    private int desiredDuration;

    /**
     * Constructs a SetTimingCommand with the concerned focus timer and input timing.
     *
     * @throws FocusException If the input duration is invalid
     */
    public SetTimingCommand(FocusTimer timer, String userInput) throws FocusException {
        this.focusTimer = timer;
        String[] parts = userInput.trim().split("\\s", 2);

        try {
            this.desiredDuration = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            String invalidIndex = "Invalid duration";
            throw new FocusException(invalidIndex);
        }
    }

    /**
     * Executes the command by resuming the timing of the timer.
     *
     */
    @Override
    public void execute() {
        focusTimer.setDuration(desiredDuration);
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
