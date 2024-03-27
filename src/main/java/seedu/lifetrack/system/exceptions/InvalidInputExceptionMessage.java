package seedu.lifetrack.system.exceptions;

public class InvalidInputExceptionMessage {

    private static final String HEADER = "\t Invalid input!\n";
    private static final String CALORIES_IN_INPUT = "\t Example input: calories in DESCRIPTION " +
            "c/INTEGER_CALORIES date/DATE [m/MACROS]";
    private static final String CALORIES_OUT_INPUT = "\t Example input: calories out DESCRIPTION " +
            "c/INTEGER_CALORIES date/DATE";
    private static final String MACROS_INPUT = "\t Example input: ....... m/CARBS_INT, PROTEIN_INT, FATS_INT";
    private static final String HYDRATION_ADD_INPUT = "\t Example input: hydration add DESCRIPTION " +
            "v/VOLUME date/DATE ";

    private static final String HYDRATION_IN_INPUT = "\t Example input: liquids in b/Milo v/1000" ;

    public static String getIncorrectOrderMessage() {
        String message = "\t Please ensure that you have keyed in the correct format in the correct order!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }

    public static String getMissingKeywordsMessage() {
        String message = "\t Please ensure that the compulsory keywords exist!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }

    public static String getWhitespaceInInputMessage() {
        String message = "\t Please ensure that there is no whitespace in your input!\n";
        return HEADER + message + CALORIES_IN_INPUT;
    }
    public static String getIncompleteMacrosMessage() {
        String message = "\t Please ensure that all macronutrients fields are filled up!\n";
        return HEADER + message + MACROS_INPUT;
    }

    public static String getWhitespaceInMacrosInputMessage() {
        String message = "\t Please ensure that there is no whitespace in your macros input!\n";
        return HEADER + message + MACROS_INPUT;
    }
    
    public static String getMacrosInCaloriesOutMessage() {
        String message = "\t Calorie output entry cannot have macros!\n";
        return HEADER + message + CALORIES_OUT_INPUT;
    }

    public static String getHydrationMissingKeywordMessage() {
        String message = "\t Please ensure that you have entered all keywords!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getHydrationIncorrectOrderMessage() {
        String message = "\t Please ensure that you have keyed the input in the correct order!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getHydrationEmptyDescriptionMessage() {
        String message = "\t Please ensure that beverage and volume is not empty!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }

    public static String getHydrationNonIntegerVolumeMessage() {
        String message = "\t Please ensure that positive integer value is keyed in for volume!\n";
        return HEADER + message + HYDRATION_IN_INPUT;
    }
}
