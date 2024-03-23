package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepTracker;

public class GetSleepCommand implements Command {
    SleepTracker sleepTracker;
    String date;

    public GetSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (sleepCommandArgs.isEmpty()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep get <date>");
        }
        this.sleepTracker = sleepTracker;
        this.date = sleepCommandArgs.trim();
    }

    @Override
    public void execute() {
        sleepTracker.getSleepCycle(date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
