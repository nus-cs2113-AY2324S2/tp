package seedu.duke;


/**
 * Represents the user interface of the Omnitravel bot
 * It contains all the responses of the Omnitravel bot to the user's commands
 */
public class Ui {
    /**
     * Prints the greetings
     */
    public void printGreeting() {
        System.out.println(" ____  _      _      _  _____  ____  ____  _     _____ _    \n" +
                "/  _ \\/ \\__/|/ \\  /|/ \\/__ __\\/  __\\/  _ \\/ \\ |\\/  __// \\   \n" +
                "| / \\|| |\\/||| |\\ ||| |  / \\  |  \\/|| / \\|| | //|  \\  | |   \n" +
                "| \\_/|| |  ||| | \\||| |  | |  |    /| |-||| \\// |  /_ | |_/\\\n" +
                "\\____/\\_/  \\|\\_/  \\|\\_/  \\_/  \\_/\\_\\\\_/ \\|\\__/  \\____\\\\____/)                                                                                                                                                                                                                                                                                                                                               ");
        System.out.println("Hello");
        System.out.println("How may I assist you?");
    }

    /**
     * Prints the farewell greetings
     */
    public void printBye(){
        System.out.println("Thank you for using Omnitravel");
        System.out.println("We hope to see you again! Goodbye!");
    }

    public void printLine(){
        System.out.println("____________________________________________________________");
    }





}
