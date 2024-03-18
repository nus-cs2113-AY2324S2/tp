package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepTracker;

public class ListSleepCommand implements Command {
    private SleepTracker sleepTracker;

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
