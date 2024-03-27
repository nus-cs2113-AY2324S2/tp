package utility;

public class WorkoutConstant {

    // Workout Delimiter
    public static final String SPLIT_BY_NUMBER_OF_STATIONS = "/n:";
    public static final String SPLIT_BY_EXERCISE_TYPE = "/e:";
    public static final String SPLIT_BY_DISTANCE = "/d:";
    public static final String SPLIT_BY_TIME = "/t:";
    public static final String SPLIT_BY_DATE = "/date:";
    public static final String SPLIT_BY_SETS = "/s:";
    public static final String SPLIT_BY_REPS = "/r:";
    public static final String SPLIT_BY_WEIGHTS = "/w:";

    // INDEX
    public static final Integer STATION_NAME_INDEX = 0;
    public static final int COMMAND_INDEX = 0;
    public static final int DISTANCE_INDEX = 1;
    public static final int TIME_INDEX = 2;
    public static final int DATE_INDEX = 3;

    // KEYWORDS
    public static final String RUN = "run";
    public static final String GYM = "gym";
    public static final String ALL = "workouts";


    // HISTORY (ALL WORKOUTS) CONSTANTS
    public static final String HISTORY_WORKOUTS_HEADER = "Showing all workouts (runs and gyms):";
    public static final String HISTORY_WORKOUTS_DATA_FORMAT = "%-5s\t%-12s\t%-8s\t%-15s\t%-8s\t%-10s\t%-4s\t%-4s\t%-10s";
    public static final String HISTORY_WORKOUTS_HEADER_FORMAT = String.format(
            "%6s\t%-5s\t%-12s\t%-8s\t%-15s\t%-8s\t%-10s\t%-4s\t%-4s\t%-10s", "Index",
                    "Type", "Date" , "Distance" , "Duration" , "Pace", "Station", "Sets" , "Reps" , "Weights");
    public static final String HISTORY_WORKOUTS_DATA_HEADER_FORMAT = "%-6s\t%s";

    // Formatted Strings/Messages
    public static final String RUN_DATA_FORMAT = "%s \t%s\t\t%s\t\t%s\t\t%s";
    public static final String RUN_DATA_INDEX_FORMAT = "%d.\t\t\t%s";
    public static final String RUN_HEADER_INDEX_FORMAT = "Index\t\tType\tTime\t\tDistance\tPace\t\tDate";
    public static final String GYM_STATION_FORMAT = "%s: ";
    public static final String GYM_SET_FORMAT = "%d reps at %d KG";
    public static final String GYM_SET_INDEX_FORMAT = "\t- Set %d. %s";
    public static final String INDIVIDUAL_GYM_STATION_FORMAT = "%d sets";
    public static final int NUMBER_OF_RUN_PARAMETERS = 4;
    public static final String RUN_HEADER = "Type\tTime\t\tDistance\tPace\t\tDate";
    public static final String ADD_RUN = "Successfully added a new run session";
    public static final String ADD_GYM = "Successfully added a new gym session";











    // Workout Errors
    // STANDARDISE FORMAT ACCORDING TO TELEGRAM MESSAGE : <ERROR_TYPE>_ERROR
    // EXAMPLE : UNSPECIFIED_PARAMETER_ERROR
    // EXAMPLE : LOAD_ERROR
    // ----------------V V V V V ---- PLEASE CHECK AND MOVE TO ERROR ---- V V V V V----------------


    public static final String BLANK_INPUT_FOR_EXERCISE = "Type of exercise cannot be empty. " +
            "Please input either /e:run or /e:gym";
    public static final String STATION_GYM_FORMAT = "e.g. Bench Press /s:2 /r:4 " +
            "/w:10,20";
    public static final String BLANK_INPUT_FOR_GYM_STATION = "Fields cannot be empty" +
            "Please input " + STATION_GYM_FORMAT;

    public static final String INVALID_INPUT_FOR_EXERCISE = "Invalid input for exercise type! " +
            "Please input either /e:run or /e:gym";
    public static final String INVALID_RUN_TIME = "Invalid run time!";
    public static final String INVALID_GYM_INPUT = "Invalid gym parameters!";
    public static final String INVALID_GYM_STATION_INDEX = "Invalid gym station index!";
    public static final String INSUFFICIENT_PARAMETERS_FOR_RUN = "Insufficient parameters for run! " +
            "Example input: new /e:run /d:DISTANCE /t:TIME [/date:DATE]";
    public static final String INSUFFICIENT_PARAMETERS_FOR_GYM = "Insufficient parameters for gym! " +
            "Example input: new /e:gym /n:<number of exercise>";
    public static final String INSUFFICIENT_PARAMETERS_FOR_GYM_STATION = "Insufficient parameters for Gym Stations! " +
            "Please input [name of exercise] /s:[sets] /r:[reps] /w:[weights]";
}
