package meditracker.argument;

/**
 * Represents the morning dosage of medication to be taken.
 * Extends the Argument class.
 */
public class DosageMorningArgument extends Argument {

    /**
     * Constructs a DosageMorningArgument object with the specified optional status.
     * @param isOptional true if the argument is optional, false otherwise.
     */
    public DosageMorningArgument(boolean isOptional) {
        super(
                ArgumentName.DOSAGE_MORNING,
                "-dM",
                "What is the morning dosage of this medication?",
                "Morning dosage of medication",
                isOptional
        );
    }
}
