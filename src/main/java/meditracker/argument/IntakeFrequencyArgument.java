package meditracker.argument;

/**
 * Represents the intake frequency of medication.
 * Extends the Argument class.
 */
public class IntakeFrequencyArgument extends Argument {

    /**
     * Constructs an IntakeFrequencyArgument object with the specified optional status.
     * @param isOptional true if the argument is optional, false otherwise.
     */
    public IntakeFrequencyArgument(boolean isOptional) {
        super(
                ArgumentName.INTAKE_FREQUENCY,
                "-f",
                "How often should this medication be taken during the day?",
                "Intake frequency of medication during the day",
                isOptional,
                true
        );
    }
}
