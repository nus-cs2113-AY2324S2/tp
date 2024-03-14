package seedu.stockpal.data.product;

public class Description {
    protected String description;
    public Description(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description: " + (description.isEmpty() ? "[X]" : description);
    }
}
