package seedu.lifetrack.ui;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;

import java.util.Scanner;

/**
 * Reads user input from the console and processes it.
 * <p>
 * This method continuously reads input from the console until the user
 * inputs "bye". For each input line, it checks if it's empty and prompts
 * the user to enter a non-empty input if it is. If the input line starts
 * with "calories in", it attempts to parse the input as calorie intake
 * information using the calorieIn method from the CalorieList class.
 */
public class Ui {

    public static void readUserInput(CalorieList calorieList, LiquidList liquidList) {
        String line;
        do {
            line = new Scanner(System.in).nextLine();
            handleUserInput(line, calorieList, liquidList);
        } while (!line.equalsIgnoreCase("bye"));
    }

    public static void handleUserInput(String line, CalorieList calorieList, LiquidList liquidList) {
        if (line.trim().isEmpty()) {
            System.out.println("Please enter a non empty Input!");
        } else if (line.startsWith("calories in") || line.startsWith("calories out")) {
            calorieList.addEntry(line);
        } else if (line.startsWith("liquids in")) {
            liquidList.addEntry(line);
        } else if (line.startsWith("list")) {
            calorieList.printCalorieList();
            liquidList.printLiquidList();
        } else if (line.startsWith("delete calories")) {
            calorieList.deleteEntry(line);
        }else if (line.startsWith("delete liquids")) {
            liquidList.deleteEntry(line);
        }
    }

    public static void byeMessage() {
        System.out.println("Bye! See you again soon ^^");
    }

    public static void printEmptyInputMessage() {
        System.out.println("Please enter a non-empty input!");
    }

    public static void printLine() {
        System.out.println("\t -------------------------------------------------------------");
    }
}
