package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.Transaction;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author leongxingyu
public class OutflowCommand extends TransactionActionCommand {
    public static final String COMMAND_KEYWORD = "outflow";
    public static final String COMMAND_DESCRIPTION = "Decreases the quantity of a product from the existing amount.";

    public static final String COMMAND_USAGE = "outflow PID a/DECREMENT_AMOUNT";

    public static final String[] COMMAND_FLAGS = {
        "PID"
        , "DECREMENT_AMOUNT"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
        , "Quantity of product to remove"
    };
    private static final Logger LOGGER = Logger.getLogger(OutflowCommand.class.getName());

    private final Pid pid;

    private LocalDateTime time;
    private final Integer amountToDecrease;

    public OutflowCommand(Integer pidValue, Integer amountToDecrease) {
        this.pid = new Pid(pidValue);
        this.amountToDecrease = amountToDecrease;
    }

    @Override
    public void execute(ProductList productList, TransactionList transactionList) throws StockPalException {
        int productIndex = productList.findProductIndex(this.pid);
        if (productIndex == -1) {
            Ui.printInvalidPidMessage();
            return;
        }
        assert productList.getSize() > 0;
        boolean updateSuccessful = productList.decreaseAmount(productIndex, amountToDecrease);

        LOGGER.log(Level.INFO, Messages.MESSAGE_OUTFLOW_SUCCESS);

        if (updateSuccessful) {
            createTransaction(transactionList);
        }
    }

    /**
     * Creates a transaction and add to the transaction list.
     * @param transactionList transactionList object.
     */
    public void createTransaction(TransactionList transactionList) {
        this.time = LocalDateTime.now();
        Transaction transaction = new Transaction(pid, -amountToDecrease, time);
        transactionList.addTransaction(transaction);
    }
}
