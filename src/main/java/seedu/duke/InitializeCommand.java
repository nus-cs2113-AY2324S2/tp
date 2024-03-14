package seedu.duke;

import java.util.Scanner;

public class InitializeCommand {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Initializing...");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("Choose your job type (Robotics, Semiconductor industry, Artificial intelligence): ");
        String jobType = scanner.nextLine();

        // verify user input
        while (!jobType.equals("Robotics") && !jobType.equals("Semiconductor industry")
                && !jobType.equals("Artificial intelligence")) {
            System.out.println(
                    "Invalid job type. Please choose from Robotics, Semiconductor industry, Artificial intelligence.");
            jobType = scanner.nextLine();
        }

        System.out.println("Welcome, " + playerName + "! You have chosen a career in " + jobType + ".");

        scanner.close();
    }
}
