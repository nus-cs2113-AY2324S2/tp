package seedu.lifetrack.ui;

import seedu.lifetrack.calorielist.CalorieList;

import java.util.Scanner;

import static seedu.lifetrack.calorielist.CalorieList.calorieIn;

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
    static CalorieList calorieList;

    public Ui(CalorieList calorieList) {
        Ui.calorieList = calorieList;
    }

    public static void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            printLine();
            if (input.trim().isEmpty()) {
                printEmptyInputMessage();
            } else if (input.startsWith("calories in")) {
                calorieIn(input);
            } else if (input.startsWith("bye")) {
                byeMessage();
            }
            printLine();
        } while (!input.equalsIgnoreCase("bye"));
        scanner.close();
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
