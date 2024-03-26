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
    public static final String RUN_DISTANCE_MUST_BE_DOUBLE_ERROR = "Distance must be a double! e.g. /d:24.00";
    public static final String RUN_DISTANCE_MUST_BE_POSITIVE_ERROR = "Distance must be positive! e.g. /d:10.32";

    public static final String RUN_TIME_EMPTY_ERROR = "Time cannot be blank! e.g. /t:HH:MM:SS";
    public static final String RUN_TIME_INVALID_FORMAT_ERROR = "Time must be in HH:MM:SS format! e.g. /t:00:40:10";
    public static final String RUN_TIME_MUST_BE_INTEGER_ERROR = "Time must be in HH:MM:SS format! e.g. /t:00:40:10";

    public static final String RUN_TIME_HOURS_RANGE_ERROR = "Hours must be between 00 and 23! e.g. /t:23:40:10";
    public static final String RUN_TIME_MINUTES_RANGE_ERROR = "Minutes must be between 00 and 59! e.g. /t:23:33:10";
    public static final String RUN_TIME_SECONDS_RANGE_ERROR = "Seconds must be between 00 and 59! e.g. /t:00:40:10";

    public static final String RUN_DATE_EMPTY_ERROR = "Date cannot be blank! e.g. /date:DD-MM-YYYY";
    public static final String RUN_DISTANCE_EMPTY_ERROR = "Distance cannot be blank! e.g. /d:10.32";


    // GYM ERRORS
    public static final String NO_OF_STATION_CANNOT_BE_BLANK_ERROR = "Number of stations cannot be blank! e.g. /n:3";
    public static final String NO_OF_STATION_MUST_BE_POSITIVE_ERROR = "Number of stations must be positive! e.g. /n:4";
    public static final String NO_OF_STATION_MUST_BE_DIGIT_ERROR = "Number of stations must be a number! e.g. /n:5";


}
