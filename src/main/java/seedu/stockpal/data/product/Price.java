package seedu.stockpal.data.product;

public class Price {
    protected Double price;

    public Price(Double price) {
        this.price = (price == null || price < 0)
                ? null
                : price;
    }

    @Override
    public String toString() {
        return "Price: " + ((price == null)
                ? "[X]"
                : String.valueOf(price));
    }
}
