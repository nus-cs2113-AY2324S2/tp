package seedu.lifetrack.system.exceptions;

public class ErrorMessages {
    public static void printIndexOutOfBoundsError(){
        System.out.println("\t Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.");
    }

    public static void printNumberFormatError(){
        System.out.println("\t Please enter a valid number within the command");
    }

}
