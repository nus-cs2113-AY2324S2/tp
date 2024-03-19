package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;

public class Quantity implements CommandParameter {
    public static final Integer MAX_QUANTITY = Integer.MAX_VALUE;
    protected Integer quantity;

    public Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void updateIncreaseQuantity(Integer increaseQuantity) throws InventoryQuantityOverflowException {
        long tentativeQuantity = (long) quantity + (long) increaseQuantity;
        if (tentativeQuantity >= MAX_QUANTITY) {
            throw new InventoryQuantityOverflowException("Overflow detected. No Change to quantity.");
        }
        quantity = (int) tentativeQuantity;
    }

    public void updateDecreaseQuantity(Integer decreaseQuantity) throws InsufficientAmountException {
        if (quantity >= decreaseQuantity) {
            quantity -= decreaseQuantity;
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
}

