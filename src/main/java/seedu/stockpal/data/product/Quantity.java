package seedu.stockpal.data.product;

import seedu.stockpal.exceptions.InsufficientAmountException;

public class Quantity {
    protected Integer quantity;

    public Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void updateIncreaseQuantity(Integer increaseQuantity) {
        quantity += increaseQuantity;
    }

    public void updateDecreaseQuantity(Integer decreaseQuantity) throws InsufficientAmountException {
        if (quantity >= decreaseQuantity) {
            quantity -= decreaseQuantity;
        } else {
            throw new InsufficientAmountException("Insufficient amount in inventory");
        }
    }
}

