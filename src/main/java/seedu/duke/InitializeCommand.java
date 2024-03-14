package seedu.duke;

import seedu.duke.ui.ResponseManager;

import java.util.Scanner;


public class InitializeCommand {

    public static void main (String[] args) {
        ResponseManager responseManager = new ResponseManager();

        Scanner scanner = new Scanner(System.in);
        responseManager.printInitializationMessage();

        String playerName = scanner.nextLine();


        responseManager.printJobSelectionMessage();
        String jobType = scanner.nextLine();

        // verify user input
        while (!jobType.equals("Robotics") && !jobType.equals("Semiconductor industry")
                && !jobType.equals("Artificial intelligence")) {
            responseManager.printJobSelectionErrorMessage();
            jobType = scanner.nextLine();
        }

        System.out.println("Welcome, " + playerName + "! You have chosen a career in " + jobType + ".");

        scanner.close();
    }
}
