package meditracker.command;

import meditracker.DailyMedicationManager;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

public abstract class Command {
    private MedicationManager medicationManager;

    /**
     * Executes the command.
     *
     * @param medicationManager ArrayList of medicines.
     * @param ui Ui object.
     * @param dailyMedicationManager  ArrayList of daily Medication
     */
    public abstract void execute(MedicationManager medicationManager,
                                 Ui ui, DailyMedicationManager dailyMedicationManager);

    /**
     * Returns the boolean to exit the program.
     *
     * @return False which continues program.
     */
    public boolean isExit() {
        return false;
    }
}
