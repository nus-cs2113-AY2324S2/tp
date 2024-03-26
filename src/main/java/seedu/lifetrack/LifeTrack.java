package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.sleep.sleeplist.SleepList;
import seedu.lifetrack.ui.Ui;

import java.util.Scanner;

public class LifeTrack {

    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        CalorieList calorieList = new CalorieList();
        LiquidList liquidList = new LiquidList();
        SleepList sleepList = new SleepList();
        Scanner in = new Scanner(System.in);
        Ui.sayHello();
        assert true : "dummy assertion set to fail";
        Ui.readUserInput(calorieList,liquidList,sleepList);
        Ui.byeMessage();
        in.close();
    }

}
