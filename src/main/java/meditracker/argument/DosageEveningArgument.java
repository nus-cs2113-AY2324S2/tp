package meditracker.argument;

/**
 * Represents the evening dosage of medication to be taken.
 * Extends the Argument class.
 */
public class DosageEveningArgument extends Argument {

    /**
     * Constructs a DosageEveningArgument object with the specified optional status.
     * @param isOptional true if the argument is optional, false otherwise.
     */
    public DosageEveningArgument(boolean isOptional) {
        super(
                ArgumentName.DOSAGE_EVENING,
                "-dE",
                "What is the evening dosage of this medication?",
                "Evening dosage of medication",
                isOptional,
                true
        );
    }
}
