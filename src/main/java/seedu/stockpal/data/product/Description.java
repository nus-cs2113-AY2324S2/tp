package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;

public class Description implements CommandParameter {
    protected String description;
    public Description(String description) {
        this.description = description;
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
