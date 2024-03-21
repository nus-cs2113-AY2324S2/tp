package git;

import java.util.List;
import java.util.Scanner;

import grocery.Grocery;


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
    }

    /**
     * Prints welcome message.
     */
    public void printWelcome() {
        // LOGO causes runtest.bat to fail, failing our CI
        final String GITLOGO =
                "   _______ ______\n"
                + "  / ____(_)_  __/\n"
                + " / / __/ / / /   \n"
                + "/ /_/ / / / /    \n"
                + "\\____/_/ /_/     \n";

        System.out.println("Hello from GiT");
        System.out.println("What is your name?");
        printLine();
        System.out.println("Hello " + in.nextLine() + "!");
        displayHelp();
        System.out.println("Enter command:");
        printLine();
    }

    /**
     * Processes user input into commands and their details.
     */
    public String[] processInput() {
        String commandLine = in.nextLine();
        String[] commandParts = commandLine.strip().split(" ", 2);
        assert commandParts.length > 0 : "Failed to read user input";

        // Return an array of length 2 for executeCommand
        if (commandParts.length == 1) {
            return new String[]{commandParts[0], ""};
        } else {
            return commandParts;
        }
    }

    /**
     * Displays help message containing all possible commands.
     */
    public void displayHelp() {
        System.out.println(
                "Here are some ways you can use this app! \n" +
                        "add GROCERY: adds the item GROCERY. \n" +
                        "exp GROCERY d/EXPIRATION_DATE: sets the expiration date for GROCERY. \n" +
                        "amt GROCERY a/AMOUNT: sets the amount of GROCERY. \n" +
                        "del GROCERY: deletes GROCERY. \n" +
                        "list: shows list of all groceries you have. \n" +
                        "exit: exits the program. \n" +
                        "help: view all the possible commands."
        );
    }

    /**
     * Prints output after setting the selected grocery's expiration date.
     */
    public static void printExpSet(Grocery grocery) {
        assert !(grocery.getName().isEmpty()): "grocery name should not be empty";
        assert !(grocery.getExpiration().isEmpty()): "expiration date should not be empty";
        System.out.println(grocery.getName() + " will expire on: " + grocery.getExpiration());
    }

    /**
     * Prints output after adding a grocery.
     */
    public static void printGroceryAdded(Grocery grocery) {
        assert !(grocery.getName().isEmpty()): "grocery name should not be empty";
        System.out.println(grocery.getName() + " added!");
    }

    /**
     * Prints output after setting the selected grocery's amount.
     */
    public static void printAmtSet(Grocery grocery) {
        assert !(grocery.getAmount().isEmpty()): "grocery amount should not be empty";
        System.out.println(grocery.getName() + ": " + grocery.getAmount());
    }

    /**
     * Prints out when there are no groceries.
     */
    public static void printNoGrocery() {
        System.out.println("There's no groceries!");
    }

    /**
     * Prints all groceries.
     */
    public static void printGroceryList(List<Grocery> groceries) {
        System.out.println("Here are your groceries!");
        for (Grocery grocery: groceries) {
            System.out.println(" - " + grocery.printGrocery());
        }
    }

    /**
     * Prints output when the selected grocery is removed.
     */
    public static void printGroceryRemoved(Grocery grocery, List<Grocery> groceries) {
        System.out.println("You now have " + groceries.size() + " groceries left");
    }

    /**
     * Prints divider for user readability.
     */
    public void printLine() {
        System.out.println(DIVIDER);
    }
}
