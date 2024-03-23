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
     * @param sleepCommandArgs User input for the add sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    public AddSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        String[] userCommand = sleepCommandArgs.trim().split("/date", 2);
        if (userCommand.length != 2) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep add <hoursSlept> /date <date>");
        }
        if (userCommand[1].isBlank()) {
            throw new SleepException("Key in non-empty date");
        }
        if (userCommand[0].isBlank()) {
            throw new SleepException("Key in non-empty number of hours slept");
        }
        this.sleepTracker = sleepTracker;
        double hourSlept;
        try {
            hourSlept = Double.parseDouble(userCommand[0].trim());
        } catch (NumberFormatException e) {
            throw new SleepException("Key in valid number of hours slept");
        }
        assert !sleepCommandArgs.isEmpty() : "Sleep cycle should not be added";
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
