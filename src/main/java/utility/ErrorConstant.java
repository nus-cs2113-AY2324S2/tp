package utility;

public class ErrorConstant {

    // General Errors
    public static final String NEGATIVE_VALUE_ERROR = "Requires a positive integer!";


    // Storage Errors
    public static final String SAVE_ERROR = "File save failed. Write error occurred:";
    public static final String LOAD_ERROR = "File read error:" + "Error at ";
    public static final String CREATE_FILE_ERROR = "Unable to create file.";
    public static final String CORRUPT_ERROR = "File is corrupted. Ceasing any further data imports.";


    // Input Errors
    public static final String INVALID_COMMAND_ERROR = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER_ERROR = "Parameter(s) unspecified.";
    public static final String INVALID_PARAMETER_ERROR = "Parameter(s) invalid or out of bounds";
    public static final String NO_DATE_SPECIFIED_ERROR = "NA";

    // EXERCISE ERRORS
    public static final String UNSPECIFIED_ERROR = "Unspecified error";

    // RUN ERRORS
    public static final String DISTANCE_MUST_BE_DOUBLE_ERROR = "Distance must be a double! e.g. /d:24.00";
    public static final String DISTANCE_MUST_BE_POSITIVE_ERROR = "Distance must be positive! e.g. /d:10.32";

    public static final String RUN_TIME_BLANK_ERROR = "Time cannot be blank! e.g. /t:1:30:00";
    public static final String RUN_DATE_BLANK_ERROR = "Date cannot be blank! e.g. /date:2021-09-01";

    // GYM ERRORS
    public static final String NO_OF_STATION_CANNOT_BE_BLANK_ERROR = "Number of stations cannot be blank! e.g. /n:3";
    public static final String NO_OF_STATION_MUST_BE_POSITIVE_ERROR = "Number of stations must be positive! e.g. /n:4";
    public static final String NO_OF_STATION_MUST_BE_DIGIT_ERROR = "Number of stations must be a number! e.g. /n:5";


}
