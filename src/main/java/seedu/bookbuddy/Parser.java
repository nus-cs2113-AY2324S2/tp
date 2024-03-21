package seedu.bookbuddy;

import exceptions.BookNotFoundException;
import exceptions.InvalidBookIndexException;
import exceptions.InvalidCommandArgumentException;
import exceptions.UnsupportedCommandException;

import java.util.logging.Level;
import static seedu.bookbuddy.BookBuddy.LOGGER;

/**
 * Parses inputs from the user in order to execute the correct commands.
 */

public class Parser {
    public static final String ADD_COMMAND = "add";
    public static final String REMOVE_COMMAND = "remove";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String EXIT_COMMAND = "bye";
    public static final String HELP_COMMAND = "help";

    /**
     * Scans the user input for valid commands and handles them accordingly.
     * @param input input from the user
     * @param books ArrayList of books
     */

    public static void parseCommand(String input, BookList books) {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0].toLowerCase();
        LOGGER.log(Level.FINE, "Parsing command: {0}", command);
        int index;
        try {
            switch (command) {
            case ADD_COMMAND:
                if (inputArray.length < 2) {
                    LOGGER.log(Level.WARNING, "The add Command requires a book title", inputArray);
                    throw new InvalidCommandArgumentException("The add command requires a book title.");
                }
                books.addBook(inputArray[1]);
                break;
            case REMOVE_COMMAND:
                try {
                    index = Integer.parseInt(inputArray[1]);
                    books.deleteBook(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + inputArray[1] + " is not a valid number. " +
                            "Please enter a valid numeric index.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid book index. Please enter a valid index.");
                }
                break;
            case LIST_COMMAND:
                books.printAllBooks();
                break;
            case MARK_COMMAND:
                try {
                    index = Integer.parseInt(inputArray[1]);
                    books.markDoneByIndex(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + inputArray[1] + " is not a valid number. " +
                            "Please enter a valid numeric index.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid book index. Please enter a valid index.");
                }
                break;
            case UNMARK_COMMAND:
                try {
                    index = Integer.parseInt(inputArray[1]);
                    books.markUndoneByIndex(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + inputArray[1] + " is not a valid number. " +
                            "Please enter a valid numeric index.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid book index. Please enter a valid index.");
                }
                break;
            case HELP_COMMAND:
                Ui.helpMessage();
                break;
            case EXIT_COMMAND:
                Ui.printExitMessage();
                System.exit(0);
                break;
            default:
                LOGGER.log(Level.WARNING, "Sorry but that is not a valid command. Please try again", command);
                throw new UnsupportedCommandException("Sorry but that is not a valid command. " +
                        "Please try again or type: help");
            }
        } catch (NumberFormatException e) {
            throw new InvalidBookIndexException("Book index must be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Book not found at the provided index.");
        } catch (Exception e) { // Generic catch block for any other exceptions
            LOGGER.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage());
            System.out.println("An unexpected error occurred. Please contact support.");
        }
    }
}
