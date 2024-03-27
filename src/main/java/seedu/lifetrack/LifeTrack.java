package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.hydration.hydrationlist.HydrationList;
import seedu.lifetrack.sleep.sleeplist.SleepList;
import seedu.lifetrack.ui.Ui;
import seedu.lifetrack.user.User;

import java.io.File;

public class LifeTrack {

    public static CalorieList calorieList = new CalorieList("data/caloriesData.txt");
    public static HydrationList hydrationList = new HydrationList("data/hydrationData.txt");
    public static SleepList sleepList = new SleepList("data/sleepData.txt");
    public static User user = new User();
    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        new File("data/").mkdir();
        Ui.sayHello();
        Ui.readUserInput(calorieList,hydrationList,user,sleepList);
        Ui.byeMessage();
    }
}
