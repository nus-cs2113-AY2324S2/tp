package sleep;

import date.DateFormat;

import java.time.LocalDate;

public class SleepCycle implements Comparable<SleepCycle> {
    private double hoursSlept;
    private LocalDate dateOfSleep;

    public SleepCycle(double hours, LocalDate date) {
        this.hoursSlept = hours;
        this.dateOfSleep = date;
    }

    public double getHoursSlept() {
        return hoursSlept;
    }

    public LocalDate getDateOfSleep() {
        return dateOfSleep;
    }

    public void setHoursOfSleep(double newHours) {
        hoursSlept = newHours;
    }

    public int compareTo(SleepCycle sleepCycle) {
        LocalDate compareDate = sleepCycle.dateOfSleep;
        if (compareDate.isEqual(this.dateOfSleep)) {
            return 0;
        } else if (compareDate.isAfter(this.dateOfSleep)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return DateFormat.convertDateToString(this.getDateOfSleep()) + ": " + this.hoursSlept;
    }
}
