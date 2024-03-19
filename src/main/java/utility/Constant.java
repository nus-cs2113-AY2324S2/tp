package utility;

public class Constant {

    // Formatted Replies
    public static final Integer MAX_RUNTIME_ARRAY_LENGTH = 3;
    public static final Integer MIN_RUNTIME_ARRAY_LENGTH = 2;

    public static final Integer EXERCISE_TYPE_INDEX = 1;
    public static final String PARTITION_LINE = "____________________________________________________________";

    public static final String ADD_RUN = "Successfully added a new run";

    // Constant for Workout
    public static final String RUN = "run";
    public static final String GYM = "gym";
    public static final String ALL = "all";

    public static final String RUN_INPUT = "e:run";
    public static final String GYM_INPUT = "e:gym";


    public static final String RUN_FORMAT = "%s \t%s\t\t%s\t\t%s\t\t%s";
    public static final String PRINT_RUN_FORMAT_WITH_INDEX = "%d.\t\t\t%s";
    public static final String RUN_HEADER_WITH_INDEX_FORMAT = "Index\t\tType\tTime\t\tDistance\tPace\t\tDate";
    public static final String RUN_HEADER = "Type\\tTime\\t\\tDistance\\tPace\\t\\tDate\"";

    // Constant for Error
    public static final String INVALID_PRINT_HISTORY_FILTER = "Invalid filter! Filter is only 'all', 'run' or 'gym'";
    public static final String NO_RUNS_FOUND = "No runs found! You need to add a run first!";
    public static final String NO_HISTORY_FOUND = "No history found!";
    public static final String BLANK_INPUT_FOR_EXERCISE = "Type of exercise cannot be empty. " +
            "Please input either /e:run or /e:gym";
    public static final String INVALID_INPUT_FOR_EXERCISE = "Invalid input for exercise type! " +
            "Please input either /e:run or /e:gym";
    public static final String INVALID_RUN_TIME = "Invalid run time!";
    public static final String INVALID_GYM_INPUT = "Invalid gym parameters!";
    public static final String INVALID_GYM_STATION_INDEX = "Invalid gym station index!";
    public static final String INSUFFICIENT_PARAMETERS_FOR_RUN = "Insufficient parameters for run! " +
            "Example input: new /e:run /d:DISTANCE /t:TIME [/date:DATE]";

    public static final String INSUFFICIENT_PARAMETERS_FOR_GYM = "Insufficient parameters for gym! " +
            "Example input: new /e:gym /n:<number of exercise>";

    public static final String SPLIT_BY_SLASH = "/";
    public static final Integer HEALTH_TYPE_INDEX = 1;
    public static final String BMI_INPUT = "h:bmi";
    public static final String PERIOD_INPUT = "h:period";
    public static final String BMI = "bmi";
    public static final String PERIOD = "period";
    public static final String HEALTH_FLAG = "/h";
    public static final String HEIGHT_FLAG = "/height";
    public static final String WEIGHT_FLAG = "/weight";
    public static final String DATE_FLAG = "/date";
    public static final Integer BMI_PARAMETERS = 4;
    public static final Integer BMI_H_OFFSET = 3;
    public static final Integer BMI_HEIGHT_OFFSET = 8;
    public static final Integer BMI_WEIGHT_OFFSET = 8;
    public static final Integer DATE_OFFSET = 6;

    public static final double UNDERWEIGHT_BMI_THRESHOLD = 18.5;
    public static final double NORMAL_BMI_THRESHOLD = 24.9;
    public static final double OVERWEIGHT_BMI_THRESHOLD = 29.9;
    public static final double OBESE_BMI_THRESHOLD = 39.9;
    public static final double POWER_OF_TWO = 2.0;
    public static final double ROUNDING_FACTOR = 100.0;

    public static final String UNDERWEIGHT_MESSAGE = "You're underweight.";
    public static final String NORMAL_WEIGHT_MESSAGE = "Great! You're within normal range.";
    public static final String OVERWEIGHT_MESSAGE = "You're overweight.";
    public static final String OBESE_MESSAGE = "You're obese.";
    public static final String SEVERELY_OBESE_MESSAGE = "You're severely obese.";

    public static final String BLANK_INPUT_FOR_HEALTH = "Type of health cannot be empty. " +
            "Please input either /h:bmi or /h:period";
    public static final String INVALID_INPUT_FOR_HEALTH = "Invalid input for health type! " +
            "Please input either /h:bmi or /h:period";
    public static final String INSUFFICIENT_PARAMETERS_FOR_BMI = "Insufficient parameters for bmi! " +
            "Example input: /h:bmi /height:height /weight:weight";
    public static final String INSUFFICIENT_PARAMETERS_FOR_PERIOD = "Insufficient parameters for period! " +
            "Example input: /h:period /start:startDate /end:endDate";
    public static final String MISSING_PARAMETERS = "Missing parameter(s)";

    public static final String BMI_MESSAGE_PREFIX = "Your BMI is ";
    public static final String BMI_ADDED_MESSAGE_PREFIX = "Added: bmi | ";
    public static final String LINE = " | ";
  
    // Input Errors
    public static final String INVALID_COMMAND = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER = "Parameter(s) unspecified.";
    public static final String INVALID_PARAMETER = "Parameter(s) invalid or out of bounds";
    public static final String NO_DATE_SPECIFIED = "NA";

    // Storage Replies
    public static final String SAVE_ERROR = "File save failed.\nWrite error occurred:\n";
    public static final String MISSING_FILE = "What is your name, Captain?";
    public static final String LOAD_ERROR = "File read error:\n" + "Error at ";
    public static final String CORRUPT_ERROR = "\nFile is corrupted. Ceasing any further data imports.";
    public static final String SUCCESSFUL_LOAD = "Welcome back, Captain\n" + "Previous data has been synchronised.";
    public static final String LOG_FILE_PATH ="./pulsepilot_log.txt";
}
