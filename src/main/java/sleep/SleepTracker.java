package sleep;

import exceptions.SleepException;
import storage.sleep.SleepTrackerStorage;
import ui.Ui;

import java.time.LocalDate;

/**
 * Represents the interface for SleepTracker
 * The sleepCycleList contains all the tasks managed by Wellness360.
 */
public class SleepTracker {
    SleepCycleList sleepCycleList;

    public SleepTracker() {
        try {
            this.sleepCycleList = SleepTrackerStorage.loadSleepListFromFile();
        } catch (SleepException e){
            Ui.printMessageWithSepNewLine(e.getMessage());
        }

    }

    public void listSleepCycles() {
        sleepCycleList.listSleepCycles();
    }

    public void addSleepCycle(SleepCycle sleepCycleToAdd) throws SleepException {
        if (sleepCycleList.getSleepCycle(sleepCycleToAdd.getDateOfSleep(), false) >= 0) {
            throw new SleepException("There is already an existing sleep cycle for the date");
        }
        sleepCycleList.addSleepCycle(sleepCycleToAdd, true);
    }

    public void updateSleepCycle(LocalDate date, double newHours) {
        sleepCycleList.updateSleepCycle(date, newHours);
    }

    public void getSleepCycle(LocalDate date) {
        sleepCycleList.getSleepCycle(date, true);
    }

    public void deleteSleepCycle(LocalDate date) {
        sleepCycleList.deleteSleepCycle(date);
    }

    public void deleteSleepCyclesBefore(LocalDate date) {
        sleepCycleList.deleteSleepCyclesBefore(date);
    }

    public void deleteSleepCyclesBetween(LocalDate startDate, LocalDate endDate) {
        sleepCycleList.deleteSleepCyclesBetween(startDate, endDate);
    }

    public void saveSleepCycles() {
        SleepTrackerStorage.saveSleepListToFile(sleepCycleList.getSleepCycleList());
    }
}
