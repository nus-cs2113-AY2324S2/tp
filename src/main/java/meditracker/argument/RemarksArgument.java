package meditracker.argument;

/**
 * Remarks for medication
 */
public class RemarksArgument extends Argument {
    public RemarksArgument(boolean isOptional) {
        super(
                ArgumentName.REMARKS,
                "-r",
                "Any additional remarks?",
                "Additional remarks on medication",
                isOptional
        );
    }
}
