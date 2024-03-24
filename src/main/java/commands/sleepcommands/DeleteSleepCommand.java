package commands.sleepcommands;

import date.DateFormat;
import exceptions.SleepException;
import commands.Command;
import sleep.DeleteMode;
import sleep.SleepTracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeleteSleepCommand implements Command {
    private SleepTracker sleepTracker;
    private LocalDate startDate;
    private LocalDate endDate;
    private DeleteMode deleteMode;

    public DeleteSleepCommand(SleepTracker sleepTracker, String sleepCommandArgs) throws SleepException {
        if (sleepCommandArgs.isBlank()) {
            throw new SleepException("Please use proper format: " + System.lineSeparator()
                    + "sleep delete /date <date>" + System.lineSeparator()
                    + "OR" + System.lineSeparator()
                    + "sleep delete /before <date>" +System.lineSeparator()
                    + "OR" + System.lineSeparator()
                    + "sleep delete /from <date> /to <date>");
        }
        this.sleepTracker = sleepTracker;
        if (sleepCommandArgs.contains("/date")) {
            deleteMode = DeleteMode.FIND;
            String[] userCommand = sleepCommandArgs.trim().split("/date", 2);
            if (userCommand.length != 2 || !userCommand[0].isBlank()) {
                throw new SleepException("Please use proper format: " + System.lineSeparator()
                        + "sleep delete /date <date>" + System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /before <date>" +System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /from <date> /to <date>");
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
        } else if (sleepCommandArgs.contains("/before")) {
            deleteMode = DeleteMode.BEFORE;
            String[] userCommand = sleepCommandArgs.trim().split("/before", 2);
            if (userCommand.length != 2 || !userCommand[0].isBlank()) {
                throw new SleepException("Please use proper format: " + System.lineSeparator()
                        + "sleep delete /date <date>" + System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /before <date>" +System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /from <date> /to <date>");
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
        } else {
            deleteMode = DeleteMode.BETWEEN;
            String[] userCommandStart = sleepCommandArgs.trim().split("/from", 2);
            String[] userCommandEnd = userCommandStart[1].trim().split("/to", 2);
            if (!userCommandStart[0].isBlank() || userCommandStart.length != 2 || userCommandEnd.length != 2) {
                throw new SleepException("Please use proper format: " + System.lineSeparator()
                        + "sleep delete /date <date>" + System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /before <date>" +System.lineSeparator()
                        + "OR" + System.lineSeparator()
                        + "sleep delete /from <date> /to <date>");
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
