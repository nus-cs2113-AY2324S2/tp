package commands.sleepcommands;

import commands.Command;
import date.DateFormat;
import exceptions.SleepException;
import sleep.SleepTracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to get sleep cycles.
 */
public class GetSleepCommand implements Command {
    private SleepTracker sleepTracker;
    private LocalDate date;

    /**
     * Constructs a new GetSleepCommand object with user input.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param sleepCommandArgs User input for the get sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
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
