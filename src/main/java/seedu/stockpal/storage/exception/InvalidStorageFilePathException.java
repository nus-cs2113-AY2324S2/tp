package seedu.stockpal.storage.exception;

import seedu.stockpal.exceptions.StockPalException;

/**
 * Indicates that the given file path is of an erroneous format.
 */
public class InvalidStorageFilePathException extends StockPalException {
    public InvalidStorageFilePathException(String message) {
        super(message);
    }
}
