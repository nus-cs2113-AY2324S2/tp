package seedu.stockpal.commands;

import seedu.stockpal.exceptions.StockPalException;

public abstract class Command {
    public void execute() throws StockPalException {
        // This method is to be implemented by child classes
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
