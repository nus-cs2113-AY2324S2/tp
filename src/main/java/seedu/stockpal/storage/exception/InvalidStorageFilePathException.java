package seedu.stockpal.storage.exception;

/**
 * Indicates that the given file path is of an erroneous format.
 */
public class InvalidStorageFilePathException extends Exception {
    public InvalidStorageFilePathException(String message) {
        super(message);
    }
}
