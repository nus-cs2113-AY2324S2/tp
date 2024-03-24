package fitness;

public enum ExerciseType {
    ARMS,
    CHEST,
    ABS,
    BACK,
    LEGS;

    @Override
    public String toString() {
        String string = super.toString();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
