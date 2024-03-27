package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.sleep.sleeplist.SleepList;
import seedu.lifetrack.ui.Ui;

import java.io.File;

public class LifeTrack {

    public static CalorieList calorieList = new CalorieList("data/caloriesData.txt");
    public static LiquidList liquidList = new LiquidList("data/liquidsData.txt");
    public static SleepList sleepList = new SleepList("data/sleepData.txt");
    
    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        new File("data/").mkdir();
        Ui.sayHello();
        Ui.readUserInput(calorieList,liquidList,sleepList);
        Ui.byeMessage();
    }
}
