package seedu.stockpal.commands;

import seedu.stockpal.StockPal;
import seedu.stockpal.data.ProductList;

public abstract class ListActionCommand extends Command {
    protected ProductList productList = StockPal.productList;
}
