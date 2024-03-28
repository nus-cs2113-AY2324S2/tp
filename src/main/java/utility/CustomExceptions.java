package utility;

import storage.LogFile;
/**
 * Represents a custom exception class designed for PulsePilot to handle errors during command processing.
 */
public class CustomExceptions extends Exception {

    /**
     * Prints the error for an OutOfBounds error, and logs it in the log file as an error.
     */
    public static class OutOfBounds extends Exception {
        public OutOfBounds(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    /**
     * Prints the error for an InvalidInput error, and logs it in the log file as an error.
     */
    public static class InvalidInput extends Exception {
        public InvalidInput(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    /**
     * Prints the error for an FileReadError error, and logs it in the log file as an error.
     */
    public static class FileReadError extends Exception{
        public FileReadError(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    /**
     * Prints the error for an FileWriteError error, and logs it in the log file as an error.
     */
    public static class FileWriteError extends Exception{
        public FileWriteError(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    /**
     * Prints the error for an FileCreateError error, and logs it in the log file as an error.
     */
    public static class FileCreateError extends Exception{
        public FileCreateError(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    /**
     * Prints the error for an InsufficientInput error, and logs it in the log file as an error.
     */
    public static class InsufficientInput extends Exception {
        public InsufficientInput(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }
}
