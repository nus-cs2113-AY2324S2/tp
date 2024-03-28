package storage.sleep;

import date.DateFormat;
import exceptions.SleepException;
import sleep.SleepCycle;
import sleep.SleepCycleList;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class to handle storage of data for Sleep Tracker
 */
public class SleepTrackerStorage {
    private static final String SLEEP_FILE_PATH = "data/sleep.txt";
    private static final String ERROR_MESSAGE = "Error in loading habit tracker data from local storage";
    private static final int HOURS_POS = 0;
    private static final int DATE_POS = 1;

    /**
     * Saves list of sleep cycles added by user into a text file.
     * @param sleepList list of sleep cycles added by user to be saved
     */
    public static void saveSleepListToFile(ArrayList<SleepCycle> sleepList) {
        if (!Storage.isFileCreated(SLEEP_FILE_PATH)) {
            Storage.createFolder(SLEEP_FILE_PATH);
        }

        ArrayList<String> data = new ArrayList<>();

        for (SleepCycle sleepCycle : sleepList) {
            data.add(sleepCycle.getHoursSlept() + "," + DateFormat.convertDateToString(sleepCycle.getDateOfSleep()));
        }

        Storage.saveTasksToFile(SLEEP_FILE_PATH, data);
        Ui.printMessageWithSepNewLine("Saved list to storage file");
    }

    /**
     * Load list of sleep cycles added by user from a text file.
     * @return SleepCycleList list of sleep cycles added by user
     */
    public static SleepCycleList loadSleepListFromFile() throws SleepException {
        if (!Storage.isFileCreated(SLEEP_FILE_PATH)) {
            Storage.createFolder(SLEEP_FILE_PATH);
        }

        ArrayList<String> data = Storage.loadDataFromFile(SLEEP_FILE_PATH);
        SleepCycleList sleepCycleList = new SleepCycleList();

        for (String line : data) {
            String[] parts = line.split(",");

            if (parts.length != 2) {
                throw new SleepException(ERROR_MESSAGE);
            }
            double hour;
            LocalDate date;
            try {
                hour = Double.parseDouble(parts[HOURS_POS]);
                date = DateFormat.convertStringToDate(parts[DATE_POS]);
            } catch (NumberFormatException | DateTimeParseException e) {
                throw new SleepException(ERROR_MESSAGE);
            }
            SleepCycle sleepCycle = new SleepCycle(hour, date);
            sleepCycleList.addSleepCycle(sleepCycle, false);
        }
        return sleepCycleList;
    }
}
