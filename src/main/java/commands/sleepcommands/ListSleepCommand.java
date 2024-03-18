package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepTracker;

/**
 * Represents a command to list sleep cycles.
 */
public class ListSleepCommand implements Command {
    private SleepTracker sleepTracker;

    /**
     * Constructs a new ListSleepCommand object with user input.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param sleepCommandArgs User input for the list sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    public ListSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (!sleepCommandArgs.isEmpty()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep list");
        }
        this.sleepTracker = sleepTracker;
    }

    @Override
    public void execute() {
        sleepTracker.listSleepCycles();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
