package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;



//@@author EdmundTangg
public class HistoryCommand extends TransactionActionCommand {
    public static final String COMMAND_KEYWORD = "history";
    public static final String COMMAND_DESCRIPTION =
        "Checks the history of inflow outflow commands" +
        " of a particular PID";
    public static final String COMMAND_USAGE =
        "history PID";
    public static final String[] COMMAND_FLAGS = {
        "PID"
    };
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
    };
    private final Pid pid;

    public HistoryCommand(Integer pidValue) {
        this.pid = new Pid(pidValue);
    }

    @Override
    public void execute(ProductList productList, TransactionList transactionList) throws StockPalException {
        int productIndex = productList.findProductIndex(this.pid);

        if (productIndex == -1) {
            Ui.printInvalidPidMessage();
            return;
        }
        TransactionList.findTransactions(transactionList, pid.getPid());
    }

}
