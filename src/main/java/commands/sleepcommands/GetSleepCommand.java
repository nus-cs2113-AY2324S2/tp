package commands.sleepcommands;

import commands.Command;
import date.DateFormat;
import exceptions.SleepException;
import sleep.SleepTracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GetSleepCommand implements Command {
    private SleepTracker sleepTracker;
    private LocalDate date;

    public GetSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (sleepCommandArgs.isEmpty()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep get <date>");
        }
        try {
            this.sleepTracker = sleepTracker;
            this.date = DateFormat.convertStringToDate(sleepCommandArgs);
        } catch (DateTimeParseException e) {
            throw new SleepException("Key in valid date of sleep cycle to get" + System.lineSeparator()
                    + "E.g: 22/12/2023");
        }
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
