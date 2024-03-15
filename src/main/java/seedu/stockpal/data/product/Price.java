package seedu.stockpal.data.product;

public class Price {
    protected Double price;

    public Price(Double price) {
        this.price = (price == null || price < 0)
                ? null
                : price;
    }

    public Double getPrice() {
        return price;
    }
}
