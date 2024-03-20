package seedu.bookbuddy;

import exceptions.BookNotFoundException;
import exceptions.InvalidBookIndexException;
import exceptions.InvalidCommandArgumentException;
import exceptions.UnsupportedCommandException;

import java.util.logging.Level;
import static seedu.bookbuddy.BookBuddy.logger;

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

    /**
     * Scans the user input for valid commands and handles them accordingly.
     * @param input input from the user
     * @param books ArrayList of books
     */

    public static void parseCommand( String input, BookList books) {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0].toLowerCase();
        logger.log(Level.FINE, "Parsing command: {0}", command);
        int index;
        try {
            switch (command) {
            case ADD_COMMAND:
                if (inputArray.length < 2) {
                    logger.log(Level.WARNING, "The add Command requires a book title", inputArray);
                    throw new InvalidCommandArgumentException("The add command requires a book title.");
                }
                books.addBook(inputArray[1]);
                break;
            case REMOVE_COMMAND:
                index = Integer.parseInt(inputArray[1]);
                books.deleteBook(index);
                break;
            case LIST_COMMAND:
                books.printAllBooks();
                break;
            case MARK_COMMAND:
                index = Integer.parseInt(inputArray[1]);
                books.markDoneByIndex(index);
                break;
            case UNMARK_COMMAND:
                index = Integer.parseInt(inputArray[1]);
                books.markUndoneByIndex(index);
                break;
            case EXIT_COMMAND:
                BookBuddy.printExitMessage();
                System.exit(0);
                break;
            default:
                logger.log(Level.WARNING, "Sorry but that is not a valid command. Please try again", command);
                throw new UnsupportedCommandException("Sorry but that is not a valid command. Please try again");
            }
        } catch (NumberFormatException e) {
            throw new InvalidBookIndexException("Book index must be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Book not found at the provided index.");
        }
    }

}
