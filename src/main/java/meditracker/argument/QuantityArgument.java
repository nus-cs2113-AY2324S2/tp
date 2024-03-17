package meditracker.argument;

public class QuantityArgument extends Argument {
    public QuantityArgument(boolean isOptional) {
        super(
                "quantity",
                "-q",
                "How much of the medication do you have?",
                "Quantity of medication",
                isOptional
        );
    }
}
