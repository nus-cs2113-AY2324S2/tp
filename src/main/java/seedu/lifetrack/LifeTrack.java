package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.ui.Ui;

import java.util.Scanner;

public class LifeTrack {

    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        CalorieList calorieList = new CalorieList();
        Scanner in = new Scanner(System.in);
        Ui.sayHello();
        Ui.readUserInput(calorieList);
        Ui.byeMessage();
    }

}
