package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.sleep.sleeplist.SleepList;
import seedu.lifetrack.ui.Ui;

import java.io.File;

public class LifeTrack {

    public static CalorieList calorieList;
    public static LiquidList liquidList;
    public static SleepList sleepList;

    public static void setup() {
        new File("data/").mkdir();
        calorieList = new CalorieList("data/caloriesData.txt");
        liquidList = new LiquidList("data/liquidsData.txt");
        sleepList = new SleepList("data/sleepData.txt");
    }

    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        setup();
        Ui.sayHello();
        Ui.readUserInput(calorieList,liquidList,sleepList);
        Ui.byeMessage();
    }
}
