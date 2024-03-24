package sleep;

import date.DateFormat;

import java.time.LocalDate;

public class SleepCycle {
    private double hoursSlept;
    private LocalDate dateOfSleep;

    public SleepCycle(double hours, LocalDate date) {
        this.hoursSlept = hours;
        this.dateOfSleep = date;
    }

    public double getHoursSlept() {
        return hoursSlept;
    }

    public String getDateOfSleep() {
        return DateFormat.convertDateToString(dateOfSleep);
    }

    public void setHoursOfSleep(double newHours) {
        hoursSlept = newHours;
    }
    @Override
    public String toString() {
        return this.dateOfSleep  + ": " + this.hoursSlept;
    }
}
