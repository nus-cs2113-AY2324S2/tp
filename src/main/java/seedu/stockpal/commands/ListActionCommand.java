package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;

public abstract class ListActionCommand extends Command {
    public abstract void execute(ProductList productList) throws StockPalException;
}
