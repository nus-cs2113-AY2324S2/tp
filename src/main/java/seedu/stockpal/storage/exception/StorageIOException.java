package seedu.stockpal.storage.exception;

import seedu.stockpal.exceptions.StockPalException;

/**
 * Indicates that some error has occurred when attempting to parse and read/write data between the application
 * and the storage file.
 */
public class StorageIOException extends StockPalException {
    public StorageIOException(String message) {
        super(message);
    }
}
