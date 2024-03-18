package meditracker;

import meditracker.command.Command;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.exception.MediTrackerException;
import meditracker.logging.MediLogger;
import meditracker.medication.MedicationManager;
import meditracker.parser.Parser;
import meditracker.ui.Ui;

/**
 * The main class for the MediTracker application.
 * It initializes the user interface and runs the application loop.
 */
public class MediTracker {

    private Ui ui;
    private MedicationManager medicationManager;
    private DailyMedicationManager dailyMedicationManager;

    /**
     * Constructs a new MediTracker object and initializes the user interface.
     */
    public MediTracker() {
        ui = new Ui();
        medicationManager = new MedicationManager();
        dailyMedicationManager = new DailyMedicationManager();
    }

    /**
     * Runs the MediTracker application.
     * This method displays a welcome message, reads user commands, and processes them until the user exits the
     * application.
     * @throws MediTrackerException If an error occurs during the execution of the application.
     * @throws ArgumentNotFoundException Argument required not found
     */
    public void run() throws MediTrackerException, ArgumentNotFoundException {
        //@@author nickczh-reused
        //Reused from https://github.com/nickczh/ip
        //with minor modifications
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command command = Parser.parse(fullCommand);
            command.execute(medicationManager, dailyMedicationManager, ui);
            isExit = command.isExit();
        }
    }

    /**
     * Starts the MediTracker application.
     * It creates a new MediTracker object and calls its run() method.
     * @param args Command-line arguments.
     * @throws MediTrackerException If an error occurs during the execution of the application.
     * @throws ArgumentNotFoundException Argument required not found
     */
    public static void main(String[] args) throws MediTrackerException, ArgumentNotFoundException {
        MediLogger.initialiseLogger();
        new MediTracker().run();
    }
}
