package meditracker.argument;

public class NameArgument extends Argument {
    public NameArgument(boolean isOptional) {
        super(
                "name",
                "-n",
                "What is the name of the medication?",
                "Name of medication",
                isOptional
        );
    }
}
