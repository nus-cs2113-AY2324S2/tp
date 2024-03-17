package meditracker.argument;

public class IntakeFrequencyArgument extends Argument {
    public IntakeFrequencyArgument(boolean isOptional) {
        super(
                "intakeFrequency",
                "-f",
                "How often should this medication be taken?",
                "Intake frequency of medication",
                isOptional
        );
    }
}
