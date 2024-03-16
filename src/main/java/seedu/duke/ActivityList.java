package seedu.duke;

import java.util.ArrayList;

public class ActivityList {
    private ArrayList<Activity> activities;

    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    public ActivityList(ArrayList<Activity> activities) {
        this.activities = activities;
    }
}
