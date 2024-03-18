package meditracker.command;

import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

public abstract class Command {
    private MedicationManager medicationManager;

    /**
     * Executes the command.
     *
     * @param medicationManager ArrayList of medicines.
     * @param ui Ui object.
     */
    public abstract void execute(MedicationManager medicationManager, Ui ui);

    /**
     * Returns the boolean to exit the program.
     *
     * @return False which continues program.
     */
    public boolean isExit() {
        return false;
    }
}
