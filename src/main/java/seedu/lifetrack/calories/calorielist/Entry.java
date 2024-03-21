package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.calories.activity.Activity;
import seedu.lifetrack.calories.Calorie;

public class Entry {

    private Activity activity;
    private Calorie calorie;

    public Entry(Activity activity, Calorie calorie){
        this.calorie = calorie;
        this.activity= activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public Calorie getCalorie() {
        return calorie;
    }

    public static String toString(Entry toPrint){
        return "Activity: " + toPrint.getActivity().getDescription()
                + ", Calories: " + toPrint.getCalorie().getCalories();
    }
}
