package meditracker.argument;

/**
 * Dosage of medication
 */
public class DosageArgument extends Argument {
    public DosageArgument(boolean isOptional) {
        super(
                ArgumentName.DOSAGE,
                "-d",
                "How much medication should you take?",
                "Dosage of medication",
                isOptional
        );
    }
}
