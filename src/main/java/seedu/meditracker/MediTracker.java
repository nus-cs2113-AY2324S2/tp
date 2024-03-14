package seedu.meditracker;

import seedu.meditracker.ui.Ui;

/**
 * The main class for the MediTracker application.
 * It initializes the user interface and runs the application loop.
 */
public class MediTracker {

    private Ui ui;

    /**
     * Constructs a new MediTracker object and initializes the user interface.
     */
    public MediTracker() {
        ui = new Ui();
    }

    /**
     * Runs the MediTracker application.
     * This method displays a welcome message, reads user commands, and processes them until the user exits the application.
     */
    public void run() {

        //@@author nickczh-reused
        //Reused from https://github.com/nickczh/ip
        //with minor modifications
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            if (command.equals("exit")) {
                isExit = true;
                ui.showExitMessage();
                continue;
            }

            ui.showLine(); // show the divider line ("_________")
            System.out.println(command + "\n");
        }
    }

    /**
     * The main method that starts the MediTracker application.
     * It creates a new MediTracker object and calls its run() method.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new MediTracker().run();
    }
}
