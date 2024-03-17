package meditracker.medication;

import java.util.ArrayList;
import java.util.List;

/**
 * The MedicationManager class represents a list of medications.
 * It contains an ArrayList of Medication objects.
 */
public class MedicationManager {
    /** The list of medications stored in an ArrayList. */
    public List<Medication> medications = new ArrayList<>();

    /**
     * Constructs an empty MedicationList.
     */
    public MedicationManager() {
    }

    /**
     * Constructs a MedicationManager with the specified list of medications.
     * @param medications The list of medications to be stored.
     */
    public MedicationManager(List<Medication> medications) {
        this.medications = medications;
    }
}
