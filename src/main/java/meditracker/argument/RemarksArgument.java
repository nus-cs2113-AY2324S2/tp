package meditracker.argument;

public class RemarksArgument extends Argument {
    public RemarksArgument(boolean isOptional) {
        super(
                "remarks",
                "-r",
                "Any additional remarks?",
                "Additional remarks on medication",
                isOptional
        );
    }
}
