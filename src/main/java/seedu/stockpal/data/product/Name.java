package seedu.stockpal.data.product;

public class Name {
    protected String name;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNull() {
        return this.name == null;
    }

    @Override
    public String toString() {
        return ("Name: " + name);
    }
}
