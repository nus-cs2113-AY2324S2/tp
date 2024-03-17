package byteceps.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {
    private static final String MESSAGE_WELCOME = "WELCOME TO BYTECEPS";
    private static final String MESSAGE_GOODBYE = "GOODBYE FOR NOW. STAY HARD!";
    private static final String SEPARATOR = "-------------------------------------------------";
    private final Scanner in;
    private final PrintStream out;

    public UserInterface() {
        this(System.in, System.out);
    }
    public UserInterface(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static void printMessage(String message) {
        System.out.printf("[ByteCep]> %s", message);
    }

    public void printWelcomeMessage() {
        out.println(SEPARATOR);
        out.println(MESSAGE_WELCOME);
        out.println(SEPARATOR);
    }

    public void printGoodbyeMessage() {
        out.println(SEPARATOR);
        out.println(MESSAGE_GOODBYE);
        out.println(SEPARATOR);
    }

    public String getUserInput() {
        String userInput;
        do {
            out.printf("[%s]> ", System.getProperty("user.name"));
            userInput = in.nextLine().trim();
        } while (userInput.trim().isEmpty());

        return userInput;
    }
}
