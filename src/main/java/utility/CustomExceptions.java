package utility;

import storage.LogFile;
/**
 * Represents a custom exception class designed for PulsePilot to handle errors during command processing.
 */
public class CustomExceptions extends Exception {

    public static class OutOfBounds extends Exception {
        public OutOfBounds(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    public static class InvalidInput extends Exception {
        public InvalidInput(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    public static class FileWriteError extends Exception{
        public FileWriteError(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }

    public static class FileCreateError extends Exception{
        public FileCreateError(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
            LogFile.writeLog(message, true);
        }
    }
}
