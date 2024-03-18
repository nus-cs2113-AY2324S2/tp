package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepCycle;
import sleep.SleepTracker;

/**
 * Represents a command to add sleep cycles.
 */
public class AddSleepCommand implements Command {
    SleepTracker sleepTracker;
    SleepCycle sleepCycleToAdd;

    /**
     * Constructs a new AddSleepCommand object with user input.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param sleepCommandArgs User input for the list sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
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
