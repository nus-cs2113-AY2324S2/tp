package meditracker.argument;

/**
 * Represents the afternoon dosage of medication to be taken.
 * Extends the Argument class.
 */
public class DosageAfternoonArgument extends Argument {

    /**
     * Constructs a DosageAfternoonArgument object with the specified optional status.
     * @param isOptional true if the argument is optional, false otherwise.
     */
    public DosageAfternoonArgument(boolean isOptional) {
        super(
                ArgumentName.DOSAGE_AFTERNOON,
                "-dA",
                "What is the afternoon dosage of this medication?",
                "Afternoon dosage of medication",
                isOptional,
                true
        );
    }
}
