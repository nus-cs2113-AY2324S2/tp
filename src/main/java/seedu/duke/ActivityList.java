package seedu.duke;

import java.util.ArrayList;
import java.util.Random;

public class ActivityList {
    private ArrayList<Activity> activities;

    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    public ActivityList(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public Activity get(int i) {
        return activities.get(i);
    }

    public int size() {
        return activities.size();
    }

    public Activity getRandomActivity() {
        Random random = new Random();
        int activityIndex = random.nextInt(9);
        return activities.get(activityIndex);
    }

    public Activity getFilteredActivity(String preferredLocation, String preferredPrice) {
        ArrayList<Activity> filteredActivities = new ArrayList<>();
        for (Activity eachActivity : activities) {
            if (eachActivity.location.equals(preferredLocation) && eachActivity.price.equals(preferredPrice)) {
                filteredActivities.add(eachActivity);
            }
        }
        Random random = new Random();
        int filteredActivityIndex = random.nextInt(filteredActivities.size());
        return filteredActivities.get(filteredActivityIndex);
    }
}
