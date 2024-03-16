package sleep;

public class SleepCycle {
    private double hoursSlept;
    private String dateOfSleep;

    public SleepCycle(double hours, String date) {
        this.hoursSlept = hours;
        this.dateOfSleep = date;
    }

    public double getHoursSlept() {
        return hoursSlept;
    }

    public String getDateOfSleep() {
        return dateOfSleep;
    }

    @Override
    public String toString() {
        return this.dateOfSleep  + ": " + this.hoursSlept;
    }
}
