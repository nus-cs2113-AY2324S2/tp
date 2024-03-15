package seedu.meditracker.medication;

import java.util.ArrayList;

/**
 * The MedicationList class represents a list of medications.
 * It contains an ArrayList of Medication objects.
 */
public class MedicationList {
    /** The list of medications stored in an ArrayList. */
    public List<Medication> medications = new ArrayList<>();

    /**
     * Constructs an empty MedicationList.
     */
    public MedicationList() {
    }

    /**
     * Constructs a MedicationList with the specified list of medications.
     * @param medications The list of medications to be stored.
     */
    public MedicationList(ArrayList<Medication> medications) {
        this.medications = medications;
    }
}
