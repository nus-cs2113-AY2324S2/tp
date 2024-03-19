package seedu.stockpal.storage.exception;

/**
 * Indicates that some error has occurred when attempting to parse and read/write data between the application
 * and the storage file.
 */
public class StorageIOException extends Exception {
    public StorageIOException(String message) {
        super(message);
    }
}
