package meditracker.medication;

/**
 * The Medication class represents a medication.
 * It stores information about the medication such as name, quantity, dosage, expiry date, intake frequency, remarks,
 * and whether it has been taken.
 */
public class Medication {

    // Medication attributes are intentionally declared String. To be modified later.
    private String name;
    private String quantity;
    private String dosage;
    private String expiryDate;
    private String intakeFreq;
    private String remarks;

    /**
     * Constructs a Medication object with the specified information.
     * @param name The name of the medication.
     * @param quantity The quantity of the medication.
     * @param dosage The dosage of the medication.
     * @param expiryDate The expiry date of the medication.
     * @param intakeFreq The intake frequency of the medication.
     * @param remarks Any remarks or notes about the medication.
     */
    public Medication(String name, String quantity, String dosage, String expiryDate,
                      String intakeFreq, String remarks) {
        this.name = name;
        this.quantity = quantity;
        this.dosage = dosage;
        this.expiryDate = expiryDate;
        this.intakeFreq = intakeFreq;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIntakeFreq() {
        return intakeFreq;
    }

    public void setIntakeFreq(String intakeFreq) {
        this.intakeFreq = intakeFreq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return getName() + " | " + getQuantity() + " | " + getDosage();
    }
}
