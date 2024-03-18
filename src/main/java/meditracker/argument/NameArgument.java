package meditracker.argument;

/**
 * Name of medication
 */
public class NameArgument extends Argument {
    public NameArgument(boolean isOptional) {
        super(
                ArgumentName.NAME,
                "-n",
                "What is the name of the medication?",
                "Name of medication",
                isOptional
        );
    }
}
