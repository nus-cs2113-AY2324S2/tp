package meditracker.argument;

/**
 * ArgumentName enum to standardise the enum values for use
 * by Command classes and ArgumentParser
 */
public enum ArgumentName {
    DOSAGE("dosage"),
    DOSAGE_MORNING("dosageMorning"),
    DOSAGE_AFTERNOON("dosageAfternoon"),
    DOSAGE_EVENING("dosageEvening"),
    EXPIRATION_DATE("expirationDate"),
    INTAKE_FREQUENCY("intakeFrequency"),
    REPEAT("repeat"),
    LIST_INDEX("listIndex"),
    NAME("name"),
    QUANTITY("quantity"),
    REMARKS("remarks"),
    LIST_TYPE("listType");

    public final String value;

    ArgumentName(String value) {
        this.value = value;
    }
}
