package seedu.duke;

public class Activity extends Favourites {
    protected String location;
    protected String price;
    protected String completionStatus;

    public Activity(String name, String location, String price, String completionStatus) {
        super(name);
        this.location = location;
        this.price = price;
        this.completionStatus = "U";
    }

    @Override
    public String toString() {
        return description;
    }

    public void markComplete() {
        this.completionStatus = "C";
    }
}
