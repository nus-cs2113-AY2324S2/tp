package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;
import sleep.SleepTracker;

public class UpdateSleepCommand implements Command {
    SleepTracker sleepTracker;
    String date;
    double hours;

    public UpdateSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        String[] userCommand = sleepCommandArgs.trim().split("/new", 2);
        if (userCommand.length != 2) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep update <date> /new <hours>");
        }
        if (userCommand[0].isBlank()) {
            throw new SleepException("Key in non-empty date");
        }
        if (userCommand[1].isBlank()) {
            throw new SleepException("Key in non-empty number of hours to update to");
        }
        try {
            this.sleepTracker = sleepTracker;
            this.date = userCommand[0].trim();
            this.hours = Double.parseDouble(userCommand[1].trim());
        } catch (NumberFormatException e) {
            throw new SleepException("Key in valid number of hours to update to");
        }

    }

    @Override
    public void execute() {
        sleepTracker.updateSleepCycle(date, hours);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
