package ui;

import java.util.ArrayList;

/**
 * Ui class handles user interface related functionalities
 * such as displaying messages,prompting user input, and
 * printing separators.
 */
public class Ui {
    private static final String BOT_NAME = "Wellness360";
    private static final String SEP = "_______________________________________________________" +
            "_________________________________________________________";

    /**
     * Greets the user upon starting the application.
     * Displays a welcome message including the chatbot's name.
     */
    public static void greetUser() {
        System.out.println(SEP + "\nHello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?\n" + SEP);
    }

    /**
     * Prints the prompt for user input.
     */
    public static void promptUserInput() {
        System.out.print("You:");
    }

    /**
     * Says goodbye to the user upon exiting the application.
     * Displays a farewell message.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n" + SEP);
    }

    /**
     * Prints a message followed by a separator and a new line.
     *
     * @param message the message to be printed
     */
    public static void printMessageWithSepNewLine(String message) {
        System.out.println(SEP + "\n" +message + "\n" + SEP);
    }

    public static void printMessageWithoutSepNewLine(String message) {
        System.out.println(SEP + "\n" + message + SEP);
    }


    public static void printList(ArrayList list, String message) {
        System.out.println(SEP);
        System.out.println(message);
        for (int i = 0; i < list.size(); i ++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
        System.out.println(SEP);
    }

}
