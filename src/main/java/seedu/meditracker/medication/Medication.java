package seedu.meditracker.medication;

/**
 * The Medication class represents a medication.
 * It stores information about the medication such as name, quantity, dosage, expiry date, intake frequency, remarks,
 * and whether it has been taken.
 */
public class Medication {

    // Medication attributes are intentionally declared String. To be modified later.
    private String medicationName;
    private String medicineQuantity;
    private String medicineDosage;
    private String expiryDate;
    private String intakeFreq;
    private String remarks;
    private boolean isTaken = false;

    /**
     * Constructs a Medication object with the specified information.
     * @param medicationName The name of the medication.
     * @param medicineQuantity The quantity of the medication.
     * @param medicineDosage The dosage of the medication.
     * @param expiryDate The expiry date of the medication.
     * @param intakeFreq The intake frequency of the medication.
     * @param remarks Any remarks or notes about the medication.
     */
    public Medication(String medicationName, String medicineQuantity, String medicineDosage, String expiryDate,
                      String intakeFreq, String remarks) {
        this.medicationName = medicationName;
        this.medicineQuantity = medicineQuantity;
        this.medicineDosage = medicineDosage;
        this.expiryDate = expiryDate;
        this.intakeFreq = intakeFreq;
        this.remarks = remarks;
    }

    /**
     * Gets the status of whether the medication has been taken.
     * @return true if the medication has been taken, false otherwise.
     */
    public boolean getIsTaken() {
        return isTaken;
    }
}
