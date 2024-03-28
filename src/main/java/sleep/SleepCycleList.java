package sleep;

import date.DateFormat;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the lists of sleep cycles
 */
public class SleepCycleList {
    private ArrayList<SleepCycle> sleepCycleList;
    private double totalHrsSlept;
    private int numberOfCycles;

    /**
     * Constructs a new SleepCycleList object with an empty list.
     */
    public SleepCycleList() {
        this.sleepCycleList = new ArrayList<>();
        this.totalHrsSlept = 0;
        this.numberOfCycles = 0;
    }

    /**
     * Adds a new sleep cycle into sleepCycleList.
     * @param sleepCycle sleep cycle to be added
     * @param isPrint true if user wants to print out message, false otherwise
     */
    public void addSleepCycle(SleepCycle sleepCycle, boolean isPrint) {
        sleepCycleList.add(sleepCycle);
        totalHrsSlept += sleepCycle.getHoursSlept();
        numberOfCycles += 1;
        if (isPrint) {
            Ui.printMessageWithSepNewLine("--- SleepCycle for "
                    + DateFormat.convertDateToString(sleepCycle.getDateOfSleep())
                    + " has been added ---");
        }
        Collections.sort(sleepCycleList);
    }

    /**
     * Deletes a new sleep cycle in sleepCycleList.
     * @param date date of sleep cycle to be deleted
     */
    public void deleteSleepCycle(LocalDate date) {
        for (int i = 0; i < numberOfCycles; i++) {
            SleepCycle currSleepCycle = sleepCycleList.get(i);
            if (date.isEqual(currSleepCycle.getDateOfSleep())) {
                totalHrsSlept -= currSleepCycle.getHoursSlept();
                sleepCycleList.remove(i);
                numberOfCycles--;
                Ui.printMessageWithSepNewLine("Sleep cycle for " + DateFormat.convertDateToString(date)
                        + " has been removed from list");
                return;
            }
        }
        Ui.printMessageWithSepNewLine("No entry for sleep cycle on " + DateFormat.convertDateToString(date));
    }

    /**
     * Deletes a new sleep cycle in sleepCycleList.
     * @param date date at which sleep cycles logged before this date are to be deleted
     */
    public void deleteSleepCyclesBefore(LocalDate date) {
        int numberOfDeletion = 0;
        while (numberOfCycles > 0 && sleepCycleList.get(0).getDateOfSleep().isBefore(date)) {
            totalHrsSlept -= sleepCycleList.get(0).getHoursSlept();
            sleepCycleList.remove(0);
            numberOfCycles--;
            numberOfDeletion++;
        }
        Ui.printMessageWithSepNewLine("A total of " + numberOfDeletion + " sleep cycles have been deleted");
    }

    /**
     * Deletes a new sleep cycle in sleepCycleList.
     * @param startDate lower bound of date of sleep cycles to be deleted
     * @param endDate upper bound of date of sleep cycles to be deleted
     */
    public void deleteSleepCyclesBetween(LocalDate startDate, LocalDate endDate) {
        int startId = 0;
        int numberOfDeletion = 0;
        while (startId < numberOfCycles) {
            SleepCycle currSleepCycle = sleepCycleList.get(startId);
            if (startDate.isBefore(currSleepCycle.getDateOfSleep())) {
                startId++;
            } else {
                break;
            }
        }
        while (startId < numberOfCycles) {
            SleepCycle currSleepCycle = sleepCycleList.get(startId);
            if (!currSleepCycle.getDateOfSleep().isAfter(endDate)) {
                sleepCycleList.remove(startId);
                numberOfDeletion++;
                numberOfCycles--;
            } else {
                break;
            }
        }
        Ui.printMessageWithSepNewLine("A total of " + numberOfDeletion + " sleep cycles have been deleted");
    }

    /**
     * List out all sleep cycles in sleepCycleList
     */
    public void listSleepCycles() {
        String sleepListMessage = "Total hrs slept: " + totalHrsSlept + System.lineSeparator();
        for (int i = 0; i < numberOfCycles - 1; i++) {
            sleepListMessage += (i + 1) + ". " + sleepCycleList.get(i) + System.lineSeparator();
        }
        if (numberOfCycles > 0) {
            sleepListMessage += numberOfCycles + ". " + sleepCycleList.get(numberOfCycles - 1);
        } else {
            sleepListMessage += "No sleep cycle has been added";
        }
        Ui.printMessageWithSepNewLine(sleepListMessage);
    }

    /**
     * Prints out number of hours of a date's sleep cycle in sleepCycleList.
     * @param date date of sleep cycle to be found
     * @param isPrint true if user wants to print out message, false otherwise
     * @return id of sleepCycle of the specific date
     */
    public int getSleepCycle(LocalDate date, boolean isPrint) {
        for (int i = 0; i < numberOfCycles; i++) {
            SleepCycle currSleep = sleepCycleList.get(i);
            if (currSleep.getDateOfSleep().isEqual(date)){
                if (isPrint) {
                    Ui.printMessageWithSepNewLine("Hours slept on " + DateFormat.convertDateToString(date)
                            + ": " + currSleep.getHoursSlept());
                }
                return i;
            }
        }
        if (isPrint) {
            Ui.printMessageWithSepNewLine("No entry found for the date.");
        }
        return -1;
    }

    /**
     * Updates a  sleep cycle in sleepCycleList.
     * @param date date of sleep cycle to be updated
     * @param newHours updated hours of sleep cycle
     */
    public void updateSleepCycle(LocalDate date, double newHours) {
        for (int i = 0; i < numberOfCycles; i++) {
            SleepCycle currSleep = sleepCycleList.get(i);
            if (currSleep.getDateOfSleep().isEqual(date)){
                double oldHours = currSleep.getHoursSlept();
                currSleep.setHoursOfSleep(newHours);
                totalHrsSlept = totalHrsSlept - oldHours + newHours;
                Ui.printMessageWithSepNewLine("Hours of sleep for " + DateFormat.convertDateToString(date) +
                        " has been updated from " + oldHours + " to " + newHours);
                return;
            }
        }
        Ui.printMessageWithSepNewLine("No entry found for the date.");
    }

    public int getNumberOfCycles() {
        return numberOfCycles;
    }

    public double getTotalHrsSlept() {
        return totalHrsSlept;
    }

    public ArrayList<SleepCycle> getSleepCycleList() {
        return this.sleepCycleList;
    }

}
