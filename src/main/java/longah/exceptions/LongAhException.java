package longah.exceptions;

import longah.LongAh;

public class LongAhException extends Exception {
    /**
     * Constructor for LongAhExceptions.
     * 
     * @param message The message to be displayed when the exception is thrown.
     */
    public LongAhException(String message) {
        super(message);
    }

    /**
     * Constructor for LongAhExceptions.
     * 
     * @param message The cause of the exception using enum {@link ExceptionMessages}.
     */
    public LongAhException(ExceptionMessage message) {
        super(message.getMessage());
    }

    /**
     * Prints the exception message.
     * 
     * @param e The exception to be printed.
     */
    public static void printException(LongAhException e) {
        System.out.println(e.getMessage());
    }
}
