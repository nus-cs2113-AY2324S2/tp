package stockPal.data.product;

public class Product {
    protected Name name;
    protected Quantity quantity;
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
}
