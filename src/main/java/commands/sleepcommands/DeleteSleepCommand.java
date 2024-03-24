package commands.sleepcommands;

import date.DateFormat;
import exceptions.SleepException;
import commands.Command;
import sleep.DeleteMode;
import sleep.SleepTracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to delete sleep cycles.
 */
public class DeleteSleepCommand implements Command {
    private static final String ERROR_MESSAGE = "Please use proper format: " + System.lineSeparator()
            + "sleep delete /date <date>" + System.lineSeparator()
            + "OR" + System.lineSeparator()
            + "sleep delete /before <date>" +System.lineSeparator()
            + "OR" + System.lineSeparator()
            + "sleep delete /from <date> /to <date>";
    private SleepTracker sleepTracker;
    private LocalDate startDate;
    private LocalDate endDate;
    private DeleteMode deleteMode;

    /**
     * Constructs a new DeleteSleepCommand object with user input.
     *
     * @param sleepTracker Class that contains information and functions to be executed required by Sleep Tracker
     * @param sleepCommandArgs User input for the delete sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    public DeleteSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (sleepCommandArgs.isBlank()) {
            throw new SleepException(ERROR_MESSAGE);
        }
        this.sleepTracker = sleepTracker;
        if (sleepCommandArgs.contains("/date")) {
            deleteSleep(sleepCommandArgs);
        } else if (sleepCommandArgs.contains("/before")) {
            deleteSleepBefore(sleepCommandArgs);
        } else {
            deleteSleepBetween(sleepCommandArgs);
        }
    }

    /**
     * Further parse user input when user wants to delete with deleteMode = FIND.
     *
     * @param sleepCommandArgs User input for the delete sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    private void deleteSleep(String sleepCommandArgs) throws SleepException {
        deleteMode = DeleteMode.FIND;
        String[] userCommand = sleepCommandArgs.trim().split("/date", 2);
        if (userCommand.length != 2 || !userCommand[0].isBlank()) {
            throw new SleepException(ERROR_MESSAGE);
        }
        if (userCommand[1].isBlank()) {
            throw new SleepException("Key in non-empty date");
        }
        LocalDate date;
        try {
            date = DateFormat.convertStringToDate(userCommand[1].trim());
        } catch (DateTimeParseException e) {
            throw new SleepException("Key in valid date of sleep cycle to delete" + System.lineSeparator()
                    + "E.g: 22/12/2023");
        }
        startDate = date;
        endDate = date;
    }

    /**
     * Further parse user input when user wants to delete with deleteMode = BEFORE.
     *
     * @param sleepCommandArgs User input for the delete sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    private void deleteSleepBefore(String sleepCommandArgs) throws SleepException {
        deleteMode = DeleteMode.BEFORE;
        String[] userCommand = sleepCommandArgs.trim().split("/before", 2);
        if (userCommand.length != 2 || !userCommand[0].isBlank()) {
            throw new SleepException(ERROR_MESSAGE);
        }
        if (userCommand[1].isBlank()) {
            throw new SleepException("Key in non-empty date");
        }
        LocalDate date;
        try {
            date = DateFormat.convertStringToDate(userCommand[1].trim());
        } catch (DateTimeParseException e) {
            throw new SleepException("Key in valid date of sleep cycle to delete" + System.lineSeparator()
                    + "E.g: 22/12/2023");
        }
        startDate = date;
        endDate = date;
    }

    /**
     * Further parse user input when user wants to delete with deleteMode = BETWEEN.
     *
     * @param sleepCommandArgs User input for the delete sleep cycles command.
     * @throws SleepException if there is any formatting issues.
     */
    private void deleteSleepBetween(String sleepCommandArgs) throws SleepException {
        deleteMode = DeleteMode.BETWEEN;
        String[] userCommandStart = sleepCommandArgs.trim().split("/from", 2);
        String[] userCommandEnd = userCommandStart[1].trim().split("/to", 2);
        if (!userCommandStart[0].isBlank() || userCommandStart.length != 2 || userCommandEnd.length != 2) {
            throw new SleepException(ERROR_MESSAGE);
        }
        if (userCommandEnd[0].isBlank()) {
            throw new SleepException("Key in non-empty start date");
        }
        if (userCommandEnd[1].isBlank()) {
            throw new SleepException("Key in non-empty end date");
        }
        try {
            startDate = DateFormat.convertStringToDate(userCommandEnd[0].trim());
            endDate = DateFormat.convertStringToDate(userCommandEnd[1].trim());
        } catch (DateTimeParseException e) {
            throw new SleepException("Key in valid date of sleep" + System.lineSeparator()
                    + "E.g: 22/12/2023");
        }
        if (endDate.isBefore(startDate)) {
            throw new SleepException("Start date must be before end date");
        }
    }

    @Override
    public void execute() throws SleepException{
        switch(deleteMode) {
        case FIND:
            sleepTracker.deleteSleepCycle(startDate);
            break;
        case BEFORE:
            sleepTracker.deleteSleepCyclesBefore(endDate);
            break;
        case BETWEEN:
            sleepTracker.deleteSleepCyclesBetween(startDate, endDate);
            break;
        default:
            throw new SleepException("Error in detection of deletion mode");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
