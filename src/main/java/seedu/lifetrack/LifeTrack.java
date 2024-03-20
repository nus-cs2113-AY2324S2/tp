package seedu.lifetrack;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.system.Ui;
import seedu.lifetrack.liquids.liquidlist.LiquidList;

import java.util.Scanner;

public class LifeTrack {

    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        CalorieList calorieList = new CalorieList();
        LiquidList liquidList = new LiquidList();
        Scanner in = new Scanner(System.in);
        String logo = "LLLLL  IIIII FFFFF EEEEE  TTTTT RRRR   AAA  CCC  K  K\n" +
                "L       I    F     E        T   R   R A   A C  C K K\n" +
                "LLL     I    FFFF  EEEE     T   RRRR  AAAAA C    KK\n" +
                "L       I    F     E        T   R  R A   A C  C K K\n" +
                "LLLLL IIIII F     EEEEE    TTTT R   R A   A  CCC K  K\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you want to do today?\n");
        Ui.readUserInput(calorieList, liquidList);
        byeMessage();
    }
  
    public static void byeMessage() {
        System.out.println("Bye! See you again soon ^^");
    }
}
