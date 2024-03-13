package seedu.lifetrack;

import seedu.lifetrack.calorielist.CalorieList;
import seedu.lifetrack.ui.Ui;

import java.util.Scanner;

public class LifeTrack {
    /**
     * Main entry-point for the java.lifetrack.LifeTrack application.
     */
    public static void main(String[] args) {
        String logo = "LLLLL  IIIII FFFFF EEEEE  TTTTT RRRR   AAA  CCC  K  K\n" +
                "L       I    F     E        T   R   R A   A C  C K K\n" +
                "LLL     I    FFFF  EEEE     T   RRRR  AAAAA C    KK\n" +
                "L       I    F     E        T   R  R A   A C  C K K\n" +
                "LLLLL IIIII F     EEEEE    TTTT R   R A   A  CCC K  K\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        CalorieList calorieList = new CalorieList();
        Ui.readUserInput();
        byeMessage();
    }

    public static void byeMessage() {
        System.out.println("Bye! See you again soon ^^");
    }
}
