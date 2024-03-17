package meditracker.medication;

import java.util.ArrayList;
import java.util.List;

/**
 * The MedicationManager class represents a list of medications.
 * It contains an ArrayList of Medication objects.
 */
public class MedicationManager {
    /** The list of medications stored in an ArrayList. */
    private List<Medication> medications = new ArrayList<>();

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

    /**
     * Gets the size of list of medications
     * @return Total number of medications
     */
    public Integer getTotalMedications() {
        return medications.size();
    }

    /**
     * Adds a Medication to the list of Medication
     *
     * @param medication Medication to be added to the list
     */
    public void addMedication(Medication medication) {
        medications.add(medication);
    }
}
