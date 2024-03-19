package meditracker.medication;

import meditracker.ui.Ui;

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

    /**
     * Gets the Medication object from the medications list.
     * Also converts the index to 0-based indexing before being used.
     *
     * @param listIndex Index of the medications list to get (1-based indexing)
     * @return Medication object at the corresponding index (0-based indexing)
     * @throws IndexOutOfBoundsException Out of range index specified
     */
    public Medication getMedication(int listIndex) throws IndexOutOfBoundsException {
        listIndex--; // Decremented to 0-base indexing
        return medications.get(listIndex);
    }

    public List<Medication> getMedications() {
        return medications;
    }

    /**
     * Deletes the Medication object from the medications list.
     * Also converts the index to 0-based indexing before being used.
     *
     * @param listIndex Index of the medications list to delete (1-based indexing)
     * @throws IndexOutOfBoundsException Out of range index specified
     */
    public void removeMedication(int listIndex) throws IndexOutOfBoundsException {
        listIndex--; // Decremented to 0-base indexing
        medications.remove(listIndex);
    }

    public void printAllMedications() {
        System.out.println("You have " + getTotalMedications() + " medications listed below.");
        Ui ui = new Ui();
        ui.printMedsList(medications);
    }
}
