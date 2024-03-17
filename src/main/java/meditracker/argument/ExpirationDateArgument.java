package meditracker.argument;

public class ExpirationDateArgument extends Argument {
    public ExpirationDateArgument(boolean isOptional) {
        super(
                "expirationDate",
                "-e",
                "When does the medication expire?",
                "Expiration date of medication",
                isOptional
        );
    }
}
