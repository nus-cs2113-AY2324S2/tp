package commands.sleepcommands;

import exceptions.SleepException;
import sleep.SleepTracker;
import commands.Command;

/**
 * Represents a command to save sleep cycles.
 */
public class SaveSleepCommand implements Command{
    private SleepTracker sleepTracker;

    /**
     * Constructs a new SaveSleepCommand object with user input.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param sleepCommandArgs User input for the save sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    public SaveSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (!sleepCommandArgs.isEmpty()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep save");
        }
        assert sleepCommandArgs.isEmpty() : "Sleep cycle should not be saved";
        this.sleepTracker = sleepTracker;
    }

    @Override
    public void execute() {
        sleepTracker.saveSleepCycles();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
