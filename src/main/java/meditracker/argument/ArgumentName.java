package meditracker.argument;

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
