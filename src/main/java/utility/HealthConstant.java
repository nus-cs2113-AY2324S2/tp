package utility;

public class HealthConstant {

    // Headers
    public static final String BMI = "bmi";
    public static final String PERIOD = "period";
    public static final String PREDICT = "predict";
    public static final String APPOINTMENT = "appointment";


    // Flags
    public static final String HEALTH_FLAG = "/h:";
    public static final String HEIGHT_FLAG = "/height:";
    public static final String WEIGHT_FLAG = "/weight:";
    public static final String DATE_FLAG = "/date:";
    public static final String START_FLAG = "/start:";
    public static final String END_FLAG = "/end:";
    public static final String TIME_FLAG = "/time:";
    public static final String DESCRIPTION_FLAG = "/description:";

    // Parameters
    public static final Integer NUM_BMI_PARAMETERS = 4;
    public static final Integer PERIOD_PARAMETERS = 3;
    public static final Integer APPOINTMENT_PARAMETERS = 4;


    // Offset
    public static final Integer H_OFFSET = 3;
    public static final Integer HEIGHT_OFFSET = 8;
    public static final Integer WEIGHT_OFFSET = 8;
    public static final Integer DATE_OFFSET = 6;
    public static final Integer START_DATE_OFFSET = 7;
    public static final Integer END_DATE_OFFSET = 5;
    public static final Integer TIME_OFFSET = 6;
    public static final Integer DESCRIPTION_OFFSET = 13;


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
    public static final String PREDICT_INPUT = "h:predict";
    public static final String APPOINTMENT_INPUT = "h:appointment";


    // Formatted Strings/Messages
    // BMI
    public static final String LOG_DELETE_BMI_FORMAT = "Removed BMI entry of %.2f from %s";
    public static final String BMI_MESSAGE_PREFIX = "Your BMI is ";
    public static final String BMI_ADDED_MESSAGE_PREFIX = "Added: bmi | ";
    public static final String BMI_REMOVED_MESSAGE_PREFIX = "Removed BMI with index: ";
    public static final String UNDERWEIGHT_MESSAGE = "You're underweight.";
    public static final String NORMAL_WEIGHT_MESSAGE = "Great! You're within normal range.";
    public static final String OVERWEIGHT_MESSAGE = "You're overweight.";
    public static final String OBESE_MESSAGE = "You're obese.";
    public static final String SEVERELY_OBESE_MESSAGE = "You're severely obese.";

    // PERIOD
    public static final String PRINT_PERIOD_FORMAT = "Period Start: %s Period End: %s"
            + System.lineSeparator()
            + "Period Length: %d days";
    public static final String LOG_DELETE_PERIOD_FORMAT = "Removed period entry with start date: %s and end date: %s";
    public static final String PERIOD_ADDED_MESSAGE_PREFIX = "Added: period | ";
    public static final String PERIOD_REMOVED_MESSAGE_PREFIX = "Removed period with index: ";

    // PREDICTION
    public static final String PRINT_CYCLE_FORMAT = "Cycle Length: %d days";
    public static final Integer LATEST_THREE_CYCLE_LENGTHS = 3;
    public static final Integer MINIMUM_SIZE_FOR_PREDICTION = 4;
    public static final String PREDICTED_START_DATE_MESSAGE ="Your next cycle's predicted start date is ";
    public static final String COUNT_DAYS_MESSAGE = ", in ";
    public static final String PERIOD_IS_LATE = ". Your period is late by ";
    public static final String DAYS_MESSAGE = " days.";

    // APPOINTMENT
    public static final String PRINT_APPOINTMENT_FORMAT = "On %s at %s: %s";
    public static final String LOG_DELETE_APPOINTMENT_FORMAT = "Removed appointment on %s at %s: %s";
    public static final String APPOINTMENT_ADDED_MESSAGE_PREFIX = "Added: appointment | ";
    public static final String APPOINTMENT_REMOVED_MESSAGE_PREFIX = "Removed appointment with index: ";
}
