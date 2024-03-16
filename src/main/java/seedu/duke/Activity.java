package seedu.duke;

public class Activity extends Favourites {
    protected String location;
    protected String price;

    public Activity(String name, String location, String price) {
        super(name);
        this.location = location;
        this.price = price;
    }
}
