package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.calories.Activity;

public class OutputEntry extends Entry {

    private Activity activity;

    public OutputEntry(String description, int calories, String date) {
        super(description, calories, date);
    }

    public OutputEntry(String description, int calories, String date, Activity activity) {
        super(description, calories, date);
        this.activity = activity;
    }
}
