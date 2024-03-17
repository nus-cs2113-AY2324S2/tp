package seedu.stockpal.data.product;

public class Description {
    protected String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isNull() {
        return this.description == null;
    }

    @Override
    public String toString() {
        return "Description: " + ((description == null || description.isEmpty())
                ? "[X]"
                : description);
    }
}
