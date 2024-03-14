package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;

public abstract class Command {

    protected ProductList productList;
    public void execute() {
        // This method is to be implemented by child classes
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
