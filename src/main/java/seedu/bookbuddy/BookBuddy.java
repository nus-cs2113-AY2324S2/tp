package seedu.bookbuddy;

import exceptions.UnsupportedCommandException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;


public class BookBuddy {
    private static BookList books = new BookList();
    static final Logger LOGGER = getLogger(BookBuddy.class.getName());
    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "BookBuddy application started.");
        printWelcomeMessage();
        getUserInput(books);
        LOGGER.log(Level.INFO, "BookBuddy application is shutting down.");
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello! We are BookBuddy!");
        System.out.println("How can I help you today?");
    }

    public static void getUserInput(BookList books) {
        Scanner input = new Scanner(System.in);
        LOGGER.log(Level.INFO, "Starting to get user input.");

        //noinspection InfiniteLoopStatement
        while (true) {
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                // If the input is empty, do not call parseCommand and just prompt for input again.
                continue;
            }
            assert !userInput.isEmpty() : "User input should not be empty at this point";
            LOGGER.log(Level.FINE, "Processing user input: {0}", userInput);
            try {
                Parser.parseCommand(userInput, books);
            } catch (UnsupportedCommandException e) {
                LOGGER.log(Level.WARNING, "Unsupported command: {0}", userInput);
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printExitMessage() {
        System.out.println("Thank you for using BookBuddy! Hope to see you again!");
    }

}
