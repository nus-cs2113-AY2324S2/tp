package seedu.lifetrack.ui;

import seedu.lifetrack.calorielist.CalorieList;
import java.util.Scanner;

/**
 * Reads user input from the console and processes it.
 *
 * This method continuously reads input from the console until the user
 * inputs "bye". For each input line, it checks if it's empty and prompts
 * the user to enter a non-empty input if it is. If the input line starts
 * with "calories in", it attempts to parse the input as calorie intake
 * information using the calorieIn method from the CalorieList class.
 */
public class Ui {

    public static void readUserInput(CalorieList calorieList) {
        String line;
        Scanner in = new Scanner(System.in);
        if(in.hasNextLine()) {
            line = in.nextLine();
            while (!line.equalsIgnoreCase("bye")) {
                if (line.trim().isEmpty()) {
                    System.out.println("Please enter a non empty Input!");
                } else if (line.startsWith("calories in") || line.startsWith("calories out")) {
                    calorieList.addEntry(line);
                }

                if(in.hasNextLine()) {
                    line = in.nextLine();
                }
            }
        }
    }
}
