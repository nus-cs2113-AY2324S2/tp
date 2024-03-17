package meditracker.argument;

public class DosageArgument extends Argument {
    public DosageArgument(boolean isOptional) {
        super(
                ArgumentName.DOSAGE,
                "-d",
                "How much medication should you take?",
                "Dosage of medication (ml)",
                isOptional
        );
    }
}
