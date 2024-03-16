package seedu.duke;

public class Food extends Favourites {
    protected String location;
    protected String price;

    public Food(String name, String location, String price) {
        super(name);
        this.location = location;
        this.price = price;
    }

    @Override
    public String toString() {
        return (description);
    }
}
