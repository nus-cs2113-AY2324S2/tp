package utility;

public class ErrorConstant {

    // General Errors
    public static final String NEGATIVE_VALUE_ERROR = "Requires a positive integer!";


    // Storage Errors
    public static final String SAVE_ERROR = "File save failed.\nWrite error occurred:\n";
    public static final String LOAD_ERROR = "File read error:\n" + "Error at ";
    public static final String CORRUPT_ERROR = "\nFile is corrupted. Ceasing any further data imports.";


    // Input Errors
    public static final String INVALID_COMMAND_ERROR = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER_ERROR = "Parameter(s) unspecified.";
    public static final String INVALID_PARAMETER_ERROR = "Parameter(s) invalid or out of bounds";
    public static final String NO_DATE_SPECIFIED_ERROR = "NA";

}
