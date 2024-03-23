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
    private static final String LOGO =
            "__        __   _ _                     _____  __    ___  \n" +
            "\\ \\      / /__| | |_ __   ___  ___ ___|___ / / /_  / _ \\ \n" +
            " \\ \\ /\\ / / _ \\ | | '_ \\ / _ \\/ __/ __| |_ \\| '_ \\| | | |\n" +
            "  \\ V  V /  __/ | | | | |  __/\\__ \\__ \\___) | (_) | |_| |\n" +
            "   \\_/\\_/ \\___|_|_|_| |_|\\___||___/___/____/ \\___/ \\___/\n";

    /**
     * Greets the user upon starting the application.
     */
    public static void greetUser() {
        System.out.println("Welcome to Wellness360!\n" + SEP);
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
        Ui.printMessageWithSepNewLine("Goodbye! See you again!");
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
