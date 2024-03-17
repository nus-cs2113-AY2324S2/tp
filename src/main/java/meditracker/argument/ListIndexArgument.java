package meditracker.argument;

public class ListIndexArgument extends Argument {
    public ListIndexArgument(boolean isOptional) {
        super(
                "listIndex",
                "-l",
                "What is the index of item in list?",
                "Index of item in list",
                isOptional
        );
    }
}
