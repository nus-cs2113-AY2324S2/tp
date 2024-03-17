package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;

public class Name implements CommandParameter {
    protected String name;

    public Name(String name) {
        this.name = name;
    }

    public boolean isNull() {
        return this.name == null;
    }

    @Override
    public String toString() {
        return ("Name: " + name);
    }
}
