package seedu.stockpal.data.product;

public class Name {
    protected String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ("Name: " + name);
    }
}
