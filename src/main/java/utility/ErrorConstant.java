package utility;

public class ErrorConstant {

    // General Errors
    public static final String NEGATIVE_VALUE_ERROR = "Requires a positive integer!";
    public static final String INVALID_INDEX_DELETE_ERROR = "Invalid index to delete!";


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

    // HISTORY ERRORS
    public static final String HISTORY_RUN_EMPTY_ERROR = "No runs found! You need to add a run first!";
    public static final String HISTORY_GYM_EMPTY_ERROR = "No gyms found! You need to add a gym first!";
    public static final String HISTORY_WORKOUTS_EMPTY_ERROR = "No workouts found! You need to add " +
            "either a run or a gym first";

    // RUN ERRORS
    public static final String RUN_DISTANCE_DOUBLE_ERROR = "Distance must be a double! e.g. /d:24.00";
    public static final String RUN_DISTANCE_POSITIVE_ERROR = "Distance must be positive! e.g. /d:10.32";

    public static final String RUN_TIME_EMPTY_ERROR = "Time cannot be blank! e.g. /t:HH:MM:SS";
    public static final String RUN_TIME_INVALID_FORMAT_ERROR = "Time must be in HH:MM:SS format! e.g. /t:00:40:10";
    public static final String RUN_TIME_INTEGER_ERROR = "Time must be in HH:MM:SS format! e.g. /t:00:40:10";

    public static final String RUN_TIME_HOURS_RANGE_ERROR = "Hours must be between 00 and 23! e.g. /t:23:40:10";
    public static final String RUN_TIME_MINUTES_RANGE_ERROR = "Minutes must be between 00 and 59! e.g. /t:23:33:10";
    public static final String RUN_TIME_SECONDS_RANGE_ERROR = "Seconds must be between 00 and 59! e.g. /t:00:40:10";
    public static final String RUN_DISTANCE_EMPTY_ERROR = "Distance cannot be blank! e.g. /d:10.32";

    // GYM ERRORS
    public static final String NO_OF_STATION_BLANK_ERROR = "Number of stations cannot be blank! e.g. /n:3";
    public static final String NO_OF_STATION_POSITIVE_ERROR = "Number of stations must be positive! e.g. /n:4";
    public static final String NO_OF_STATION_DIGIT_ERROR = "Number of stations must be a number! e.g. /n:5";

    public static final String GYM_EXERCISE_NAME_BLANK_ERROR = "Exercise name cannot be blank! e.g. " +
            WorkoutConstant.STATION_GYM_FORMAT;

    public static final String GYM_SET_DIGIT_ERROR = "Number of sets must be a number! e.g. /s:4";
    public static final String GYM_SET_POSITIVE_ERROR = "Number of sets must be positive! e.g. /s:4";
    public static final String GYM_SET_BLANK_ERROR = "Number of sets cannot be blank! e.g. /s:4";

    public static final String GYM_REP_DIGIT_ERROR = "Number of reps must be a number! e.g. /r:4";

    public static final String GYM_REP_POSITIVE_ERROR = "Number of reps must be positive! e.g. /r:4";
    public static final String GYM_REP_BLANK_ERROR = "Number of reps cannot be blank! e.g. /r:4";

    public static final String GYM_WEIGHT_POSITIVE_ERROR = "Weights must be positive! e.g. /w:10,20,30";
    public static final String GYM_WEIGHT_BLANK_ERROR = "Weights cannot be blank! e.g. /w:10,20,30";

    public static final String GYM_WEIGHT_DIGIT_ERROR = " Weights must be a number! e.g. /w:5,10,20";
    public static final String GYM_WEIGHTS_INCORRECT_NUMBER_ERROR = " Number of weight values must be the same as" +
            " the number of sets! e.g. benchpress /s:2 /r:10 /w:10,20";

    public static final String GYM_STATION_INPUT_DIGIT_ERROR = "Numeric input required for sets, reps and weights!"
            + " e.g. " + WorkoutConstant.STATION_GYM_FORMAT;

    // HEALTH ERRORS
    public static final String HEALTH_INPUT_BLANK_ERROR = "Type of health cannot be empty. " +
            "Please input either /h:bmi, /h:period or /h:appointment";
    public static final String INVALID_HEALTH_INPUT_ERROR = "Invalid input for health type! " +
            "Please input either /h:bmi, /h:period or /h:appointment";

    // BMI ERRORS
    public static final String INSUFFICIENT_BMI_PARAMETERS_ERROR = "Insufficient parameters for bmi! " +
            "Example input: /h:bmi /height:height /weight:weight /date:date";
    public static final String NEGATIVE_BMI_ERROR = "Bmi must be a positive value";
    public static final String NULL_BMI_ERROR = "Bmi object cannot be null.";
    public static final String EMPTY_BMI_LIST_ERROR = "BMI List is empty.";
    public static final String BMI_LIST_UNCLEARED_ERROR = "Bmi list is not cleared.";

    // PERIOD ERRORS
    public static final String INSUFFICIENT_PERIOD_PARAMETERS_ERROR = "Insufficient parameters for period! " +
            "Example input: /h:period /start:startDate /end:endDate";
    public static final String NULL_PERIOD_ERROR = "Period object cannot be null.";
    public static final String NULL_START_DATE_ERROR = "Start date of period cannot be empty.";
    public static final String NULL_END_DATE_ERROR = "End date of period cannot be empty.";
    public static final String EMPTY_PERIOD_LIST_ERROR = "Period List is empty.";
    public static final String PERIOD_LIST_UNCLEARED_ERROR = "Period list is not cleared.";
    public static final String PERIOD_END_BEFORE_START_ERROR = "Start date of period must be before end date.";
    public static final String UNABLE_TO_MAKE_PREDICTIONS_ERROR = "Insufficient period cycles to make prediction.";

    // APPOINTMENT ERRORS
    public static final String INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR = "Insufficient parameters for period! " +
            "Example input: /h:appointment /date:date /time:time /description:description /place:place";
    public static final String NULL_APPOINTMENT_ERROR = "Appointment object cannot be null.";
    public static final String EMPTY_APPOINTMENT_LIST_ERROR = "Appointment list is empty.";
    public static final String APPOINTMENT_LIST_UNCLEARED_ERROR = "Appointment list is not cleared.";

    // HISTORY AND LATEST ERRORS
    public static final String INVALID_HISTORY_FORMAT_ERROR = "Invalid command format. " +
            System.lineSeparator() +
            "Usage: history/latest /view:filter";
    public static final String INVALID_HISTORY_FILTER_ERROR = "Missing/invalid filter used!" +
            System.lineSeparator() +
            "Use /view:run/gym/workouts/period/bmi";

    public static final String INVALID_LATEST_FILTER_ERROR = "Missing/invalid filter used!" +
            System.lineSeparator() +
            "Use /view:run/gym/period/bmi";

}
