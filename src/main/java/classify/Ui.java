package classify;

public class Ui {
    //@@author ParthGandhiNUS
    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String WELCOME_TO_CLASSIFY = "Welcome to Classify!";
    private static final String WHAT_CAN_I_DO_FOR_YOU_TODAY = "What can I do for you today?";
    private static final String CLASSIFY_GOODBYE_MESSAGE = "Hope you've had a productive day. See you again! Goodbye!";

    /**
     * Prints a dividing line between statements for added clarity
     */
    public static void printDivider(){
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the welcome message to introduce the user to Classify
     */
    public static void printWelcomeMessage(){
        printDivider();
        System.out.println(WELCOME_TO_CLASSIFY);
        System.out.println(WHAT_CAN_I_DO_FOR_YOU_TODAY);
        printDivider();
    }

    /**
     * Prints out the message to end conversation with the user
     */
    public static void printEndConversation() {
        System.out.println(CLASSIFY_GOODBYE_MESSAGE);
        printDivider();
    }
}
