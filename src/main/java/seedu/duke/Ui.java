package seedu.duke;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "_________________________________________________________________";
    static Scanner input = new Scanner(System.in);
    /** Specifies whether user has input the exit command  */
    public boolean isExit = false;

    private Parser parser = new Parser();

    /** Prints the welcome message upon the start of the application  */
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! Welcome to FitNUS");
        System.out.println("What would you like to track today?");
        System.out.println(LINE);
    }

    public void handleExit() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
        isExit = true;
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public void readCommand() {
        String command = input.nextLine();
        showLine();
        if (command.equals("exit")) {
            handleExit();
        } else {
            parser.handleCommand(command);
        }
        showLine();
    }
}
