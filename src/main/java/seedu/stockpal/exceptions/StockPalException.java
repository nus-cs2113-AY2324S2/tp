package seedu.stockpal.exceptions;

/**
 * Represents an exception for the StockPal application.
 */
public class StockPalException extends Exception {
     /**
      * Constructs a new StockPalException object with the error message.
      *
      * @param message The error message of the exception.
      */
     public StockPalException(String message) {
         super(message);
     }
}
