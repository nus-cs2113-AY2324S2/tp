package meditracker.argument;

public class IntakeFrequencyArgument extends Argument {
    public IntakeFrequencyArgument(boolean isOptional) {
        super(
                ArgumentName.INTAKE_FREQUENCY,
                "-f",
                "How often should this medication be taken?",
                "Intake frequency of medication",
                isOptional
        );
    }
}
