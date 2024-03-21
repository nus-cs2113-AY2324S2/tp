package utility;

public class HealthConstant {

    // Headers
    public static final String BMI = "bmi";
    public static final String PERIOD = "period";


    // Flags
    public static final String HEALTH_FLAG = "/h";
    public static final String HEIGHT_FLAG = "/height";
    public static final String WEIGHT_FLAG = "/weight";
    public static final String DATE_FLAG = "/date";
    public static final String START_FLAG = "/start";
    public static final String END_FLAG = "/end";


    // Parameters
    public static final Integer BMI_PARAMETERS = 4;
    public static final Integer PERIOD_CYCLE_PARAMETERS = 3;


    // Offset
    public static final Integer BMI_H_OFFSET = 3;
    public static final Integer BMI_HEIGHT_OFFSET = 8;
    public static final Integer BMI_WEIGHT_OFFSET = 8;
    public static final Integer DATE_OFFSET = 6;
    public static final Integer PERIOD_CYCLE_H_OFFSET = 3;
    public static final Integer PERIOD_CYCLE_START_OFFSET = 7;
    public static final Integer PERIOD_CYCLE_END_OFFSET = 5;


    // Index
    public static final Integer HEALTH_TYPE_INDEX = 1;


    // Threshold
    public static final double UNDERWEIGHT_BMI_THRESHOLD = 18.5;
    public static final double NORMAL_BMI_THRESHOLD = 24.9;
    public static final double OVERWEIGHT_BMI_THRESHOLD = 29.9;
    public static final double OBESE_BMI_THRESHOLD = 39.9;


    // Input
    public static final String BMI_INPUT = "h:bmi";
    public static final String PERIOD_INPUT = "h:period";


    // Formatted Strings/Messages
    public static final String PRINT_PERIOD_FORMAT = "Period Start: %s Period End: %s"
            + System.lineSeparator()
            + "Period Length: %d days";
    public static final String UNDERWEIGHT_MESSAGE = "You're underweight.";
    public static final String NORMAL_WEIGHT_MESSAGE = "Great! You're within normal range.";
    public static final String OVERWEIGHT_MESSAGE = "You're overweight.";
    public static final String OBESE_MESSAGE = "You're obese.";
    public static final String SEVERELY_OBESE_MESSAGE = "You're severely obese.";


    // STANDARDISE FORMAT ACCORDING TO TELEGRAM MESSAGE : <ERROR_TYPE>_ERROR
    // EXAMPLE : UNSPECIFIED_PARAMETER_ERROR
    // EXAMPLE : LOAD_ERROR
    // ----------------V V V V V ---- PLEASE CHECK AND MOVE TO ERROR ---- V V V V V----------------
    public static final String BLANK_INPUT_FOR_HEALTH = "Type of health cannot be empty. " +
            "Please input either /h:bmi or /h:period";
    public static final String INVALID_INPUT_FOR_HEALTH = "Invalid input for health type! " +
            "Please input either /h:bmi or /h:period";
    public static final String INSUFFICIENT_PARAMETERS_FOR_BMI = "Insufficient parameters for bmi! " +
            "Example input: /h:bmi /height:height /weight:weight";
    public static final String INSUFFICIENT_PARAMETERS_FOR_PERIOD = "Insufficient parameters for period! " +
            "Example input: /h:period /start:startDate /end:endDate";
    public static final String BMI_MESSAGE_PREFIX = "Your BMI is ";
    public static final String BMI_ADDED_MESSAGE_PREFIX = "Added: bmi | ";
    public static final String PERIOD_ADDED_MESSAGE_PREFIX = "Added: period | ";
    public static final String NUMBER_OF_INPUTS_REQUIRED_PERIOD =  "Array of userInputs should have 5 elements.";
    public static final String HEIGHT_WEIGHT_REQUIRE_POSITIVE = "Both Height and weight must be positive.";
    public static final String BMI_MUST_BE_POSITIVE = "Bmi must be a positive value";
    public static final String BMI_CANNOT_BE_NULL = "Bmi object cannot be null.";
    public static final String BMI_LIST_EMPTY = "BMI List is empty.";
    public static final String PERIOD_LIST_EMPTY = "Period List is empty.";
    public static final String PERIOD_CANNOT_BE_NULL = "Period object cannot be null.";
    public static final String PERIOD_START_MUST_BE_BEFORE_END = "Start date of period must be before end date.";
    public static final String START_DATE_CANNOT_BE_NULL = "Start date of period cannot be empty.";
    public static final String END_DATE_CANNOT_BE_NULL = "End date of period cannot be empty.";
}
