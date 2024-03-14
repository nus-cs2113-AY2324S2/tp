package seedu.stockpal.data.product;

public class Product {
    protected Name name;
    protected Quantity quantity;

    public Name getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Price getPrice() {
        return price;
    }

    public Description getDescription() {
        return description;
    }

    public Pid getPid() {
        return pid;
    }

    protected Price price;
    protected Description description;
    protected Pid pid;

    public Product(String name, Integer quantity, Double price, String description, Integer pid) {
        this.name = new Name(name);
        this.quantity = new Quantity(quantity);
        this.price = new Price(price);
        this.description = new Description(description);
        this.pid = new Pid();
    }

    @Override
    public String toString() {
        return "PID: " + getPid()
                + " Product: " + getName()
                + " Quantity: " + getQuantity()
                + " Price: " + getPrice()
                + " Quantity: " + getQuantity();
    }
}
