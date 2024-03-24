package longah.exception;

import longah.handler.UI;
import longah.handler.Logging;

public class LongAhException extends Exception {
    private static ExceptionType type;

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
     * @param message The cause of the exception using enum {@link ExceptionMessage}.
     */
    public LongAhException(ExceptionMessage message) {
        super(message.getMessage());
        type = message.getType();
    }

    /**
     * Prints the exception message.
     * 
     * @param e The exception to be printed.
     */
    public static void printException(LongAhException e) {
        UI.showMessage(e.getMessage());
        if (type == ExceptionType.WARNING) {
            Logging.logWarning(e.getMessage());
        } else if (type == ExceptionType.INFO) {
            Logging.logInfo(e.getMessage());
        }
    }

    public static boolean isMessage(LongAhException e, ExceptionMessage message) {
        return e.getMessage().equals(message.getMessage());
    }
}
