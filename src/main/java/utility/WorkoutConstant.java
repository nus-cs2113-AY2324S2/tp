package utility;

public class WorkoutConstant {
    // Delimiter
    public static final String STATION_DELIMITER = "/n:";


    // Headers
    public static final String RUN = "run";
    public static final String GYM = "gym";
    public static final String ALL = "all";

    // Input
    public static final String RUN_INPUT = "e:run";
    public static final String GYM_INPUT = "e:gym";


    // Formatted Strings/Messages
    public static final String RUN_FORMAT = "%s \t%s\t\t%s\t\t%s\t\t%s";
    public static final String PRINT_RUN_FORMAT_WITH_INDEX = "%d.\t\t\t%s";
    public static final String RUN_HEADER_WITH_INDEX_FORMAT = "Index\t\tType\tTime\t\tDistance\tPace\t\tDate";
    public static final String GYM_STATION_HEADER_WITH_INDEX_FORMAT = "Index\t\t\tExercise Name\t\tSets\tRepetition" +
            "\t\tWeights";
    public static final String GYM_STATION_FORMAT = "%s: ";
    public static final String GYM_SET_FORMAT = "%d reps at %d KG";
    public static final String INDIVIDUAL_GYM_STATION_FORMAT = "%d sets of %s";
    public static final int NUMBER_OF_RUN_PARAMETERS = 4;
    public static final String RUN_HEADER = "Type\tTime\t\tDistance\tPace\t\tDate";
    public static final String ADD_RUN = "Successfully added a new run session";
    public static final String ADD_GYM = "Successfully added a new gym session";


    // Index
    public static final Integer EXERCISE_TYPE_INDEX = 1;  // PLEASE STANDARDISE
    public static final Integer LENGTH_OF_GYM_STATION_INPUTS = 4;
    public static final Integer INDEX_OF_STATION_NAME = 0; // PLEASE STANDARDISE
    public static final Integer INDEX_OF_STATION_SETS = 1; // PLEASE STANDARDISE
    public static final Integer INDEX_OF_STATION_REPS = 2; // PLEASE STANDARDISE
    public static final Integer INDEX_OF_STATION_WEIGHTS = 3; // PLEASE STANDARDISE

    public static final String STATION_SET_DELIMITER = "s:"; // PLEASE STANDARDISE
    public static final String STATION_REPS_DELIMITER = "r:"; // PLEASE STANDARDISE
    public static final String STATION_WEIGHTS_DELIMITER = "w:"; // PLEASE STANDARDISE


    // Numerical Values
    public static final int SUBSTRING_COMMAND = 0;
    public static final int SUBSTRING_DISTANCE = 1;
    public static final int SUBSTRING_TIME = 2;
    public static final int SUBSTRING_DATE = 3;


    // Workout Errors
    // STANDARDISE FORMAT ACCORDING TO TELEGRAM MESSAGE : <ERROR_TYPE>_ERROR
    // EXAMPLE : UNSPECIFIED_PARAMETER_ERROR
    // EXAMPLE : LOAD_ERROR
    // ----------------V V V V V ---- PLEASE CHECK AND MOVE TO ERROR ---- V V V V V----------------
    public static final String INVALID_FILTER = "Invalid filter! Filter is only 'run', 'gym', 'bmi' or 'period'";
    public static final String NO_RUNS_FOUND = "No runs found! You need to add a run first!";
    public static final String NO_GYMS_FOUND = "No gyms found! You need to add a gym first!";
    public static final String NO_HISTORY_FOUND = "No history found!";
    public static final String BLANK_INPUT_FOR_EXERCISE = "Type of exercise cannot be empty. " +
            "Please input either /e:run or /e:gym";
    public static final String STATION_GYM_FORMAT = "[name of exercise:string] /s:[sets:number] /r:[reps:number] " +
            "/w:[weights:number]";
    public static final String BLANK_INPUT_FOR_GYM_STATION = "Fields cannot be empty" +
            "Please input " + STATION_GYM_FORMAT;
    public static final String NUMERIC_INPUT_REQUIRED_GYM_STATION = "Numeric input required for sets, reps and weights!"
            + "Please input " + STATION_GYM_FORMAT;
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
