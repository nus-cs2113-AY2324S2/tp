package utility;

import java.io.File;

/**
 * Constants class representing all constants used for PulsePilot.
 */
public class UiConstant {

    // Special Characters
    public static final String SPLIT_BY_SLASH = "/";
    public static final String SPLIT_BY_COLON = ":";
    public static final String SPLIT_BY_WHITESPACE = " ";
    public static final String SPLIT_BY_COMMAS = ",";
    public static final String LINE = " | ";
    public static final String PARTITION_LINE = "____________________________________________________________";
    public static final String EMPTY_STRING = "";


    // PulsePilot UI replies
    public static final String EXIT_MESSAGE = "Initiating PulsePilot landing sequence...";


    // Storage
    public static final int DATA_TYPE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final String LOG_FILE_PATH = "./pulsepilot_log.txt";
    public static final String DATA_FILE_PATH = "./pulsepilot_data.txt";
    public static final File SAVE_FILE = new File(UiConstant.DATA_FILE_PATH);
    public static final int FILE_FOUND = 0;
    public static final int FILE_NOT_FOUND = 1;
    public static final String MISSING_FILE = "What is your name, voyager?";
    public static final String SUCCESSFUL_LOAD = "Prior data found. Orbit has been synchronised.";


    // Numerical values
    public static final Integer MAX_RUNTIME_ARRAY_LENGTH = 3;
    public static final Integer MIN_RUNTIME_ARRAY_LENGTH = 2;
    public static final int NUM_SECONDS_IN_MINUTE = 60;
    public static final int NUM_SECONDS_IN_HOUR = 3600;
    public static final double POWER_OF_TWO = 2.0;
    public static final double ROUNDING_FACTOR = 100.0;
}
