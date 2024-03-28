package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Quantity implements CommandParameter {
    public static final Integer MAX_QUANTITY = Integer.MAX_VALUE;
    public static final Integer WARNING_QUANTITY = 20;

    protected static Logger logger = Logger.getLogger(Quantity.class.getName());

    protected Integer quantity;
    private boolean isLowQuantityWarningPrinted;

    public Quantity(Integer quantity, boolean isLowQuantityWarningPrinted) {
        this.quantity = quantity;
        this.isLowQuantityWarningPrinted = isLowQuantityWarningPrinted;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void updateQuantity(Integer newQuantity) {
        quantity = newQuantity;
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

    public void updateIncreaseQuantity(Quantity quantity, Integer increaseQuantity)
            throws InventoryQuantityOverflowException {
        logger.log(Level.INFO, "Updating quantity with increase: " + increaseQuantity);
        assert increaseQuantity != null : "Increase quantity cannot be null";
        long tentativeQuantity = (long) quantity.getQuantity() + (long) increaseQuantity;
        assert tentativeQuantity >= 0 : "Tentative Quantity cannot be negative";

        if (tentativeQuantity > MAX_QUANTITY) {
            logger.log(Level.WARNING, "Inventory overflow detected. No change to quantity.");
            throw new InventoryQuantityOverflowException("Overflow detected. No Change to quantity.");
        }

        assert tentativeQuantity <= MAX_QUANTITY : "Tentative quantity exceeds MAX_QUANTITY. Integer overflow detected";
        updateQuantity((int) tentativeQuantity);
        logger.log(Level.INFO, "Quantity updated successfully to: " + quantity);
    }

    /**
     * Check if the quantity can be decreased without reducing quantity to 0.
     *
     * @param decreaseQuantity User input of the amount to decrease to quantity.
     * @throws InsufficientAmountException Exception thrown when the amount to decrease is
     *         larger than the current quantity.
     */
    public void updateDecreaseQuantity(Quantity quantity, Integer decreaseQuantity) throws InsufficientAmountException {
        logger.log(Level.INFO, "Updating quantity with decrease: " + decreaseQuantity);
        assert decreaseQuantity != null : "Decrease quantity cannot be null";
        int initialQuantity = quantity.getQuantity();
        int newQuantity;

        if (initialQuantity >= decreaseQuantity) {
            newQuantity = initialQuantity - decreaseQuantity;
            assert newQuantity >= 0 : "Quantity cannot be smaller than 0.";
            notifyLowQuantity(this);
            logger.log(Level.INFO, "Quantity updated successfully to: " + newQuantity);
        } else {
            logger.log(Level.WARNING, "Insufficient amount in inventory. No change to quantity. ");
            throw new InsufficientAmountException("Insufficient amount in inventory");
        }

        updateQuantity(newQuantity);
        logger.log(Level.INFO, "Quantity updated successfully to: " + newQuantity);

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

    /**
     * Check if product is of low quantity
     *
     * @param product Product class
     * @return return true if quantity is <= WARNING_QUANTITY
     *         else return false
     */
    public boolean isLowQuantity (Product product) {
        Quantity productQuantity = product.getQuantity();
        return productQuantity.getQuantity() <= WARNING_QUANTITY;
    }

    /**
     * Check if it is the first time the product hits low quantity
     * If it is the first time, then notify
     * Else, do not notify
     *
     * @param quantity Quantity class
     */
    public void notifyLowQuantity(Quantity quantity) {
        if (quantity.getQuantity() <= WARNING_QUANTITY && !quantity.isLowQuantityWarningPrinted()) {
            Ui.printThresholdWarningAlert();
            quantity.setWarningPrinted(true);
        }
    }
}

