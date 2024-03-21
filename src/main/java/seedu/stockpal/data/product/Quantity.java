package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;
import seedu.stockpal.ui.Ui;

public class Quantity implements CommandParameter {
    public static final Integer MAX_QUANTITY = Integer.MAX_VALUE;
    public static final Integer WARNING_QUANTITY = 20;

    protected Integer quantity;
    private boolean isLowQuantityWarningPrinted;

    public Quantity(Integer quantity, boolean isLowQuantityWarningPrinted) {
        this.quantity = quantity;
        this.isLowQuantityWarningPrinted = isLowQuantityWarningPrinted;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public boolean isLowQuantityWarningPrinted() {
        return isLowQuantityWarningPrinted;
    }

    public void setWarningPrinted(boolean isPrinted) {
        isLowQuantityWarningPrinted = isPrinted;
    }

    /**
     * Checks if the new quantity will result in integer overflow,
     * else update quantity to new quantity.
     *
     * @param increaseQuantity User input of the amount to increase to quantity.
     * @throws InventoryQuantityOverflowException Exception that occurs when the new
     *         quantity is larger than the maximum integer INT can hold.
     */

    public void updateIncreaseQuantity(Integer increaseQuantity) throws InventoryQuantityOverflowException {
        long tentativeQuantity = (long) quantity + (long) increaseQuantity;
        assert tentativeQuantity >= 0 : "Tentative Quantity cannot be negative";
        if (tentativeQuantity > MAX_QUANTITY) {
            throw new InventoryQuantityOverflowException("Overflow detected. No Change to quantity.");
        }
        assert tentativeQuantity <= MAX_QUANTITY : "Tentative quantity exceeds MAX_QUANTITY. Integer overflow detected";
        quantity = (int) tentativeQuantity;
    }

    /**
     * Check if the quantity can be decreased without reducing quantity to 0.
     *
     * @param decreaseQuantity User input of the amount to decrease to quantity.
     * @throws InsufficientAmountException Exception thrown when the amount to decrease is
     *         larger than the current quantity.
     */
    public void updateDecreaseQuantity(Integer decreaseQuantity) throws InsufficientAmountException {
        assert decreaseQuantity != null : "Decrease quantity cannot be null";
        if (quantity >= decreaseQuantity) {
            quantity -= decreaseQuantity;
            assert quantity >= 0 : "Quantity cannot be smaller than 0.";
            notifyLowQuantity(this);
        } else {
            throw new InsufficientAmountException("Insufficient amount in inventory");
        }
    }

    public boolean isNull() {
        return this.quantity == null;
    }

    @Override
    public String toString() {
        return ("Quantity: " + quantity);
    }

    /**
     * Converts the Quantity to the specific format for saving to the data file.
     *
     * @return A formatted string containing the Quantity for saving.
     */
    public String toSave() {
        return this.quantity.toString();
    }

    public boolean isLowQuantity (Product product) {
        Quantity productQuantity = product.getQuantity();
        return productQuantity.getQuantity() <= WARNING_QUANTITY;
    }

    public void notifyLowQuantity(Quantity quantity) {
        if (quantity.getQuantity() <= WARNING_QUANTITY && !quantity.isLowQuantityWarningPrinted()) {
            Ui.printThresholdWarningAlert();
            quantity.setWarningPrinted(true);
        }
    }
}

