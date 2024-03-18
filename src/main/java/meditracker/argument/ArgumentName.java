package meditracker.argument;

/**
 * ArgumentName enum to standardise the enum values for use
 * by Command classes and ArgumentParser
 */
public enum ArgumentName {
    DOSAGE("dosage"),
    EXPIRATION_DATE("expirationDate"),
    INTAKE_FREQUENCY("intakeFrequency"),
    LIST_INDEX("listIndex"),
    NAME("name"),
    QUANTITY("quantity"),
    REMARKS("remarks");

    public final String value;

    ArgumentName(String value) {
        this.value = value;
    }
}
