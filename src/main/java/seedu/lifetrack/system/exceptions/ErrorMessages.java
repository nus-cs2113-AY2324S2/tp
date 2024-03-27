package seedu.lifetrack.system.exceptions;

public class ErrorMessages {

    public static void printIndexOutOfBoundsError() {
        System.out.println("\t Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.");
    }

    public static void printNumberFormatError() {
        System.out.println("\t Please enter a valid number within the command");
    }

    public static String getIncorrectCaloriesInputMessage() {
        return "\t Please input only positive integers into the calories field!";
    }

    public static String getIncorrectMacrosInputMessage() {
        return "\t Please input only positive integers into the macronutrients field!";
    }

    public static String getInvalidNumberOfSetUpInputs() {
        return "\t Sorry, this command is invalid. Please enter the setup command in the following format " +
                "user setup {NAME} h/{HEIGHT} w/{WEIGHT} a/{AGE} s/{SEX} e/{ENERGY LEVELS} g/{GOAL}";
    }

    public static String getInvalidGoalNumberMessage() {
        return "\t Invalid input for goal number. Please enter a number between 1 and 7.";
    }

    public static String getInvalidExerciseLevelsNumberMessage() {
        return "\t Invalid input for exercise level. Please enter a number between 1 and 5.";
    }
}
