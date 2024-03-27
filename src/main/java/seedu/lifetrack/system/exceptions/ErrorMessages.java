package seedu.lifetrack.system.exceptions;

public class ErrorMessages {

    public static void printIndexOutOfBoundsError(){
        System.out.println("\t Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.");
    }

    public static void printNumberFormatError(){
        System.out.println("\t Please enter a valid number within the command");
    }

    public static String getIncorrectCaloriesInputMessage() {
        return "\t Please input only positive integers into the calories field!";
    }

    public static String getIncorrectMacrosInputMessage() {
        return "\t Please input only positive integers into the macronutrients field!";
    }

    public static String getIOExceptionMessage() {
        return "\t Unable to write to file!";
    }

    public static String getFileNotFoundMessage() {
        return "\t No file found! The initialised list will be empty.";
    }

    public static String getIncorrectSleepInputMessage() {
        return "\t Please input only positive real number into the sleep duration field!";
    }
    
    public static String getIncorrectSleepDateInputMessage() {
        return "\t Error: Date must be in DDMMYY format.!";
    }
}
