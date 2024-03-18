package meditracker.argument;

/**
 * List index of Medication or DailyMedication
 */
public class ListIndexArgument extends Argument {
    public ListIndexArgument(boolean isOptional) {
        super(
                ArgumentName.LIST_INDEX,
                "-l",
                "What is the index of item in list?",
                "Index of item in list",
                isOptional
        );
    }
}
