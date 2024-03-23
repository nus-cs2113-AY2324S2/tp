package seedu.duke;

public class Food extends Favourites {
    protected String location;
    protected String price;
    protected String cuisine;

    public Food(String name, String location, String price, String cuisine) {
        super(name);
        this.location = location;
        this.price = price;
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return (description);
    }
}
