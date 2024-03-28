package seedu.duke;


import java.util.NoSuchElementException;

/**
 * Represents the user interface of the Omnitravel bot
 * It contains all the responses of the Omnitravel bot to the user's commands
 */
public class Ui {
    /**
     * Prints the greetings
     */
    public static void printGreeting() {
        printLine();
        System.out.println(" ____  _      _      _  _____  ____  ____  _     _____ _\n" +
                "/  _ \\/ \\__/|/ \\  /|/ \\/__ __\\/  __\\/  _ \\/ \\ |\\/  __// \\\n" +
                "| / \\|| |\\/||| |\\ ||| |  / \\  |  \\/|| / \\|| | //|  \\  | |\n" +
                "| \\_/|| |  ||| | \\||| |  | |  |    /| |-||| \\// |  /_ | |_/\\\n" +
                "\\____/\\_/  \\|\\_/  \\|\\_/  \\_/  \\_/\\_\\\\_/ \\|\\__/  \\____\\\\____/)");
        System.out.println("Hello");
        System.out.println("How may I assist you?");
        printLine();
    }

    /**
     * Prints the farewell greetings
     */
    public static void printBye(){
        printLine();
        System.out.println("Thank you for using Omnitravel");
        System.out.println("We hope to see you again! Goodbye!");
        printLine();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printException(OmniException exception){
        printLine();
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    public static void printNoSuchElementException(NoSuchElementException exception){
        printLine();
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    public static void printNumberTooLargeException(NumberFormatException exception) {
        printLine();
        System.out.println("Warning! " + exception.getMessage() + " number too large!");
        printLine();
    }

    public static void helpCommand(){
        System.out.println("These are the available commands!");
        System.out.println("1. list\n2. add <insert travel activity>\n3. delete <insert activity number>\n" +
                           "4. find <insert keyword>\n5. help\n6. bye\n");
    }

    public static void printDateTimeExceptionError(){
        System.out.println("Invalid date, please input the date in the following order: YYYY-MM-DD");
    }

    public static void printSavingError(){
        System.out.println("Something went wrong when saving the file");
    }
}
