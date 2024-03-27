package seedu.lifetrack.sleep.sleeplist;

import seedu.lifetrack.Entry;

public class SleepEntry extends Entry {

    private String date;
    private double duration;

    /***
     * Sleep constructor: date can be empty. If date input is empty, automatically fill with N/A;
     * date should be in format DDMMYY, duration should be a positive real number in hour unit.
     * @param date
     * @param duration
     */
    public SleepEntry (double duration, String date){
        super("SLEEP", date);
        this.date = date.isEmpty() ? "N/A" : date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public double getDuration() {
        return duration;
    }

    public String toString() {
        // Show "N/A" if no date was provided
        return "Date: " + (date == null || date.isEmpty() ? "N/A" : date) +
                ", Duration: " + String.format("%.1f", duration) + " hours";
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";" + duration);
    }
}
