package sleep;

public class SleepCycle {
    double hours_slept;
    String date_of_sleep;

    public SleepCycle(double hours, String date) {
        this.hours_slept = hours;
        this.date_of_sleep = date;
    }

    public double getHours_slept() {
        return hours_slept;
    }

    public String getDate_of_sleep() {
        return date_of_sleep;
    }

    @Override
    public String toString() {
        return this.date_of_sleep  + ": " + this.hours_slept;
    }
}
