package commands.sleepcommands;

import commands.Command;
import sleep.SleepTracker;

public class ListSleepCommand implements Command  {
    SleepTracker sleepTracker;
    public ListSleepCommand(SleepTracker sleepTracker) {
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
