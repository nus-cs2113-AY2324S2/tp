package meditracker;

import meditracker.command.Command;
import meditracker.exception.MediTrackerException;
import meditracker.medication.MedicationList;
import meditracker.parser.Parser;
import meditracker.ui.Ui;

/**
 * The main class for the MediTracker application.
 * It initializes the user interface and runs the application loop.
 */
public class MediTracker {

    private Ui ui;
    private MedicationList medicationList;

    /**
     * Constructs a new MediTracker object and initializes the user interface.
     */
    public MediTracker() {
        ui = new Ui();
        medicationList = new MedicationList();
    }

    /**
     * Runs the MediTracker application.
     * This method displays a welcome message, reads user commands, and processes them until the user exits the
     * application.
     * @throws MediTrackerException If an error occurs during the execution of the application.
     */
    public void run() throws MediTrackerException {
        //@@author nickczh-reused
        //Reused from https://github.com/nickczh/ip
        //with minor modifications
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command command = Parser.parse(fullCommand);
            command.execute(medicationList, ui);
            isExit = command.isExit();
        }
    }

    /**
     * Starts the MediTracker application.
     * It creates a new MediTracker object and calls its run() method.
     * @param args Command-line arguments.
     * @throws MediTrackerException If an error occurs during the execution of the application.
     */
    public static void main(String[] args) throws MediTrackerException {
        new MediTracker().run();
    }
}
