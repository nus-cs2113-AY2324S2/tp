package commands.focuscommands;

import commands.Command;
import exceptions.FocusException;
import focus.FocusTimer;

public class SetTimingCommand implements Command {
    private FocusTimer focusTimer;
    private int desiredDuration;

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

    @Override
    public void execute() throws FocusException {
        focusTimer.setDuration(desiredDuration);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
