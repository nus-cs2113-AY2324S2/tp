package meditracker.argument;

/**
 * Quantity of medication
 */
public class QuantityArgument extends Argument {
    public QuantityArgument(boolean isOptional) {
        super(
                ArgumentName.QUANTITY,
                "-q",
                "How much of the medication do you have?",
                "Quantity of medication",
                isOptional
        );
    }
}
