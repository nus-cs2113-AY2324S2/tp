package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.exceptions.StockPalException;

//@@author EdmundTangg
public abstract class TransactionActionCommand extends Command {
    public abstract void execute(ProductList productList, TransactionList transactionList) throws StockPalException;
}
