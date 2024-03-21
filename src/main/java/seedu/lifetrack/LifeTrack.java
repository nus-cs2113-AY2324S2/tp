package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.ui.Ui;

import java.util.Scanner;

public class LifeTrack {

    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        CalorieList calorieList = new CalorieList();
        LiquidList liquidList = new LiquidList();
        Scanner in = new Scanner(System.in);
        Ui.sayHello();
        Ui.readUserInput(calorieList,liquidList);
        Ui.byeMessage();
    }

}
