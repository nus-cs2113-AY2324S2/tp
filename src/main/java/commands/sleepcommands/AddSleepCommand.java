package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepCycle;
import sleep.SleepTracker;

public class AddSleepCommand implements Command {
    SleepTracker sleepTracker;
    SleepCycle sleepCycleToAdd;
    public AddSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (sleepCommandArgs.isEmpty()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                + "sleep add <hoursSlept> /date <date>");
        }
        String[] userCommand = sleepCommandArgs.trim().split("/date");
        this.sleepTracker = sleepTracker;
        int hourSlept;
        try {
            hourSlept = Integer.parseInt(userCommand[0].trim());
        } catch (NumberFormatException e) {
            throw new SleepException("Key in valid number of hours slept");
        }
        this.sleepCycleToAdd = new SleepCycle(hourSlept, userCommand[1].trim());
    }
    @Override
    public void execute() {
        sleepTracker.addSleepCycle(sleepCycleToAdd);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
