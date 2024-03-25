package seedu.stockpal.data.product;

import seedu.stockpal.common.Messages;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;
import seedu.stockpal.ui.Ui;

public class Product {
    private Name name;
    private Quantity quantity;
    private Price price;
    private Description description;
    private final Pid pid;

    public Product(String name, Integer quantity, Double price, String description, Integer pid) {
        this.name = new Name(name);
        this.quantity = new Quantity(quantity, false);
        this.price = new Price(price);
        this.description = new Description(description);
        this.pid = new Pid(pid);
    }

    public Name getName() {
        return this.name;
    }

    public Quantity getQuantity() {
        return this.quantity;
    }

    public Price getPrice() {
        return this.price;
    }

    public Description getDescription() {
        return this.description;
    }

    public Pid getPid() {
        return this.pid;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public boolean isPidMatch(Pid pid) {
        return this.pid.equals(pid);
    }


    /**
     * Increase quantity of the product and throw exception if overflow detected
     *
     * @param amountToChange Quantity to increase by
     */
    public boolean increaseQuantity(Integer amountToChange) {
        try {
            quantity.updateIncreaseQuantity(amountToChange);
            Ui.printToScreen("Quantity updated. " + quantity.toString());
            return true;
        } catch (InventoryQuantityOverflowException iqoe) {
            Ui.printToScreen("Overflow detected. No change to quantity. " + quantity.toString());
            return false;
        }

    }

    /**
     * Decrease quantity of the product and throw exception when outflow > current amount
     *
     * @param amountToChange Quantity to decrease by
     */
    public boolean decreaseQuantity(Integer amountToChange) {

        try {
            quantity.updateDecreaseQuantity(amountToChange);
            Ui.printToScreen("Quantity updated. " + quantity.toString());
            return true;
        } catch (InsufficientAmountException iae) {
            Ui.printToScreen("Insufficient amount in inventory. No change to quantity. " + quantity.toString());
            return false;
        }
    }

    @Override
    public String toString() {
        String separator = "  |  ";
        return (this.pid + separator + this.name + separator + this.quantity + separator + this.price
                + Messages.LINE_SEPARATOR + this.description);
    }

    /**
     * Converts the Product to the specific format for saving to the data file.
     *
     * @return A formatted string containing the Product for saving.
     */
    public String toSave() {
        String separator = ";";
        return this.pid.toSave() + separator +
                this.name.toSave() + separator +
                this.quantity.toSave() + separator +
                this.price.toSave() + separator +
                this.description.toSave();
    }
}
