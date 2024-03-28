package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;

public class Price implements CommandParameter {
    private static final String EMPTY_STRING = "";
    private final Double price;

    public Price(Double price) {
        this.price = (price == null || price < 0)
                ? null
                : price;
    }

    public Double getPrice() {
        return this.price;
    }

    public boolean isNull() {
        return this.price == null;
    }

    private boolean isEmptyPrice() {
        return isNull() || this.price.equals(0.00);
    }

    @Override
    public String toString() {
        return "Price: " + (this.isEmptyPrice()
                ? "[X]"
                : String.format("$%.2f", price));
    }

    /**
     * Converts the Price to the specific format for saving to the data file.
     *
     * @return A formatted string containing the Price for saving.
     */
    public String toSave() {
        return this.isEmptyPrice()
                ? EMPTY_STRING
                : String.format("%.2f", this.price);
    }
}
