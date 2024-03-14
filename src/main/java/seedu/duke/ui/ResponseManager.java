package seedu.duke.ui;

import seedu.duke.userprofile.Profile;

public class ResponseManager {
    private static final String INITIALIZATION_MESSAGE = "Initializing...\n"
        + "Enter your name: \n";

    public static void printInitializationMessage() {
        System.out.println(INITIALIZATION_MESSAGE);
    }

    public static void printJobSelectionMessage() {
        System.out.println("Choose your job type (Robotics, Semiconductor industry, Artificial intelligence): ");
    }

    public static void printJobSelectionErrorMessage() {
        System.out.println(
            "Invalid job type. Please choose from Robotics, Semiconductor industry, Artificial intelligence.");
    }

    public static void printWelcomeMessage(Profile profile) {
        System.out.println("Welcome, " + profile.toString());
    }

    public static void printChooseIndustryMessage(String input) {
        System.out.println("You have chosen " + input);
    }

    public static void printInvalidNameMessage() {
        System.out.println("Please enter a valid name");
    }

    public static void printNameTooLongMessage() {
        System.out.println("Name is too long");
    }
}
