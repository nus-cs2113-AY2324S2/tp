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

    private static final String WHITESPACE = "         ";

    private static final String logo = "|     IIIII  FFFFF EEEEE  TTTTT RRRR    AAA   CCC  K  K\n" +
            "|       I    F     E        T   R   R  A   A C     K K\n" +
            "|       I    FFFF  EEEE     T   RRRR   AAAAA C     KK\n" +
            "|       I    F     E        T   R  R   A   A C     K K\n" +
            "|______  IIIII F     EEEEE    T   R   R  A   A  CCC  K  K\n";

    public static void readUserInput(CalorieList calorieList, LiquidList liquidList) {
        String line;
        do {
            line = new Scanner(System.in).nextLine();
            handleUserInput(line, calorieList, liquidList);
        } while (!line.equalsIgnoreCase("bye"));
    }

    public static void handleCaloriesInput(String line, CalorieList calorieList) {
        assert !line.startsWith("bye") : "exit the app";
        if (line.startsWith("calories in") || line.startsWith("calories out")) {
            calorieList.addEntry(line);
        } else if (line.startsWith("calories list")) {
            calorieList.printCalorieList();
        } else if (line.startsWith("calories delete")) {
            calorieList.deleteEntry(line);
        } else {
            handleUnknownInput();
        }
    }

    public static void handleLiquidsInput(String line, LiquidList liquidsList) {
        assert !line.startsWith("bye") : "exit the app";
        if (line.startsWith("liquids in") || line.startsWith("liquids out")) {
            liquidsList.addEntry(line);
        } else if (line.startsWith("liquids list")) {
            liquidsList.printLiquidList();
        } else if (line.startsWith("liquids delete")) {
            liquidsList.deleteEntry(line);
        } else {
            handleUnknownInput();
        }
    }

    public static void handleUserInput(String line, CalorieList calorieList, LiquidList liquidList) {
        if (!line.startsWith("bye")) {
            printLine();
            line = line.trim().toLowerCase();
            if (line.isEmpty()) {
                printEmptyInputMessage();
            } else if (line.startsWith("calories")) {
                handleCaloriesInput(line, calorieList);
            } else if (line.startsWith("help")) {
                showHelp();
            } else if (line.startsWith("liquids")) {
                handleLiquidsInput(line, liquidList);
            } else {
                handleUnknownInput();
            }
            printLine();
        }
    }

    public static void sayHello() {
        System.out.println(WHITESPACE + "Hello from\n\n" + logo);
        System.out.println(WHITESPACE + "How can I help you today?");
        printLine();
    }

    public static void byeMessage() {
        printLine();
        System.out.println(WHITESPACE + "Bye! See you again soon ^^");
    }

    public static void printEmptyInputMessage() {
        System.out.println("\t Please enter a non-empty input!");
    }

    public static void printLine() {
        System.out.println(WHITESPACE + "-------------------------------------" +
                "----------------------------------------");
    }

    public static void handleUnknownInput() {
        System.out.println("\t Oops! I've never seen this input before...");
        System.out.println("\t If you are unsure of the commands, use the help command for a quick recap :)");

    }

    public static void showHelp() {
        System.out.println("\t LifeTrack Command List:");
        System.out.println("\t - help: Displays a list of available commands and their descriptions.");
        System.out.println("\t - calories in/out <activity> c/<number of calories> d/<date>: " +
                "Adds a calorie gaining/burning entry into the calories tracker.");
        System.out.println("\t - calories list: Displays all entries currently stored in the calorie list.");
        System.out.println("\t - calories delete <index>: Deletes the entry at the specified index" +
                " from the calorie list.");
        System.out.println("\t - hydration add b/<type of beverage> v/<volume> : " +
                "Marks the task at the specified index as done.");
        System.out.println("\t - hydration list: Displays all entries currently stored in the hydration list.\"");
    }
}
