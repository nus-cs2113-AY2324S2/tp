package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }

    // Method to check if the shot resulted in a goal
    public static boolean goalCheck(int userInput, int save) {
        return !(userInput == save); // If shoot direction matches save direction, it's not a goal and the returned value is false
    }

    public static void viewGoalBeforeShot() {
        System.out.println("_______________________________");
        System.out.println("|         |         |         |");
        System.out.println("|         |         |         |");
        System.out.println("|         |         |         |");
        System.out.println("|         |         |         |");
    }
    public static void viewGoalAfterShot(boolean goalScored) {
        if(goalScored) {
            System.out.println("GOAL!!!!");
            System.out.println("_______________________________");
            System.out.println("| *    *  |  *  *   | *      *|");
            System.out.println("|    *    |       * |     *   |");
            System.out.println("|*   *    | *   *   |  *   *  |");
            System.out.println("|      *  |    *    |*      * |");
        }
        else {
            System.out.println("no goal :((((");
            System.out.println("_______________________________");
            System.out.println("\\         \\         \\         \\");
            System.out.println(" \\         \\         \\         \\");
            System.out.println("  \\         \\         \\         \\");
            System.out.println("   \\         \\         \\         \\");
        }
    }
}

