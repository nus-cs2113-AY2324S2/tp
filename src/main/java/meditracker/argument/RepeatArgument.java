package meditracker.argument;

/**
 * Represents the repeat frequency of medication.
 * Extends the Argument class.
 */
public class RepeatArgument extends Argument {

    /**
     * Constructs a RepeatArgument object with the specified optional status.
     * @param isOptional true if the argument is optional, false otherwise.
     */
    public RepeatArgument(boolean isOptional) {
        super(
             ArgumentName.REPEAT,
                "-rep",
                "How often do you need to take this medication?",
                "How often to take medication (eg: once a day, once every week)",
                isOptional,
                true
        );
    }
}
