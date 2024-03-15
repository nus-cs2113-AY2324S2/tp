package meditracker.command;

import meditracker.medication.MedicationList;
import meditracker.ui.Ui;

/**
 * The ExitCommand class represents a command to exit the application.
 * It extends the Command class.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * This method displays the exit message using the provided user interface.
     * @param medicationList The MedicationList object (not used in this command).
     * @param ui The Ui object used to interact with the user interface.
     */
    @Override
    public void execute(MedicationList medicationList, Ui ui) {
        ui.showExitMessage();
    }

    /**
     * Checks if the command is an exit command.
     * @return true indicating that this is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
