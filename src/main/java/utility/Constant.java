package utility;

public class Constant {

    // Formatted Replies
    public static final Integer MAX_RUNTIME_ARRAY_LENGTH = 3;
    public static final Integer MIN_RUNTIME_ARRAY_LENGTH = 2;

    public static final Integer EXERCISE_TYPE_INDEX = 1;
    public static final String PARTITION_LINE = "____________________________________________________________";

    public static final String ADD_RUN = "Successfully added a new run";


    public static final String NO_DATE_SPECIFIED = "NA";

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
}
