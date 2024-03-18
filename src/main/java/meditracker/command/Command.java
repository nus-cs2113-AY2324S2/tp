package meditracker.command;

import meditracker.DailyMedicationManager;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

public abstract class Command {
    private MedicationManager medicationManager;

    /**
     * Executes the command.
     *
     * @param medicationManager      ArrayList of medicines.
     * @param dailyMedicationManager List of dailyMedication
     * @param ui                     Ui object.
     */
    public abstract void execute(MedicationManager medicationManager,
                                 DailyMedicationManager dailyMedicationManager,
                                 Ui ui);

    /**
     * Returns the boolean to exit the program.
     *
     * @return False which continues program.
     */
    public boolean isExit() {
        return false;
    }
}
