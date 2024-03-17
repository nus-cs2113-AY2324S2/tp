package meditracker.argument;

public class DosageArgument extends Argument {
    public DosageArgument(boolean isOptional) {
        super(
                "dosage",
                "-d",
                "How much medication should you take?",
                "Dosage of medication (ml)",
                isOptional
        );
    }
}
