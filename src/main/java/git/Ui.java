package git;

import java.util.Scanner;

import exceptions.GitException;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    // ATTRIBUTES
    public static final String DIVIDER = "- - - - -";
    private Scanner in;

    // METHODS
    /**
     * Constructs Ui and initialises Scanner to read input.
     */
    public Ui() {
        in = new Scanner(System.in);
        printWelcome();
    }

    /**
     * Prints welcome message.
     */
    private void printWelcome() {
        // LOGO causes runtest.bat to fail, failing our CI
        final String GITLOGO =
                "   _______ ______\n"
                + "  / ____(_)_  __/\n"
                + " / / __/ / / /   \n"
                + "/ /_/ / / / /    \n"
                + "\\____/_/ /_/     \n";

        System.out.println("Hello from GiT");
        System.out.println("What is your name?");
        System.out.println("Hello " + in.nextLine() + "!");
        System.out.println("Enter command:");
    }

    /**
     * Processes user input into commands and their details.
     */
    public String[] processInput() throws GitException {
        String commandLine = in.nextLine();
        return commandLine.strip().split(" ", 2);
    }

    /**
     * Displays help message containing all possible commands.
     */
    public void displayHelp() {
        System.out.println(
                "Here are some ways you can use this app!\n" +
                        "add GROCERY: adds the item GROCERY\n" +
                        "exp GROCERY d/EXPIRATION_DATE: sets the expiration date for GROCERY\n" +
                        "amt GROCERY a/AMOUNT: sets the amount of GROCERY\n" +
                        "del GROCERY: deletes GROCERY\n" +
                        "list: shows list of all groceries you have\n" +
                        "exit: exits the program."
        );
    }

    /**
     * Prints divider for user readability.
     */
    public void printLine() {
        System.out.println(DIVIDER);
    }
}
