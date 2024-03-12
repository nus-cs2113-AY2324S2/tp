package byteceps.ui;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_WELCOME = "    WELCOME TO BYTECEPS";
    private static final String MESSAGE_GOODBYE = "    GOODBYE FOR NOW. STAY HARD!";
    private static final String SEPARATOR = "   -------------------------------------------------";

    public void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        System.out.println(SEPARATOR);
    }

    public void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(SEPARATOR);
    }

    public String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine.trim();
    }
}
