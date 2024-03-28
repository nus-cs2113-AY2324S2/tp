package seedu.stockpal.exceptions;

public class InventoryQuantityOverflowException extends StockPalException {
    public InventoryQuantityOverflowException(String message) {
        super(message);
    }
}
