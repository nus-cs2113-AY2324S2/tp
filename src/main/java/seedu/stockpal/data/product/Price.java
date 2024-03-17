package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;

public class Price implements CommandParameter {
    protected Double price;

    public Price(Double price) {
        this.price = (price == null || price < 0)
                ? null
                : price;
    }

    public boolean isNull() {
        return this.price == null;
    }
    @Override
    public String toString() {
        return "Price: " + ((price == null)
                ? "[X]"
                : String.format("%.2f", price));
    }
}
