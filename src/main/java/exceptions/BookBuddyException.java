package exceptions;

/**
 * Representation of an exception unique to BookBuddy
 */

public class BookBuddyException extends Exception {
    public BookBuddyException(String message) {
        super(message);
    }
}
