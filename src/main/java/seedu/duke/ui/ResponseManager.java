package seedu.duke.ui;

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
        System.out.println("Invalid job type. Please choose from Robotics, Semiconductor industry, Artificial intelligence.");
    }
}
