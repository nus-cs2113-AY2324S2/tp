package commands.sleepcommands;

import commands.Command;
import date.DateFormat;
import exceptions.SleepException;
import sleep.SleepTracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UpdateSleepCommand implements Command {
    private SleepTracker sleepTracker;
    private LocalDate date;
    private double hours;

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
            date = DateFormat.convertStringToDate(userCommand[0].trim());
            hours = Double.parseDouble(userCommand[1].trim());
        } catch (DateTimeParseException e) {
            throw new SleepException("Key in valid date of sleep cycle to be updated" + System.lineSeparator()
                    + "E.g: 22/12/2023");
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
