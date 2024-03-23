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
public class InflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_DESCRIPTION = "Increases the quantity of a product from the existing amount.";
    public static final String COMMAND_USAGE = "inflow PID a/INCREMENT_AMOUNT";

    public static final String[] COMMAND_FLAGS = {
        "PID"
        , "INCREMENT_AMOUNT"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
        , "Quantity of product to add"
    };

    private static final Logger LOGGER = Logger.getLogger(InflowCommand.class.getName());

    private final Pid pid;
    private final Integer amountToIncrease;
    private LocalDateTime time;

    public InflowCommand(Integer pidValue, Integer amountToIncrease) {
        this.pid = new Pid(pidValue);
        this.amountToIncrease = amountToIncrease;
    }

    @Override
    public void execute(ProductList productList) throws StockPalException {
        TransactionList transactionList = new TransactionList();
        int productIndex = productList.findProductIndex(this.pid);
        if (productIndex == -1) {
            Ui.printInvalidPidMessage();
            return;
        }
        productList.increaseAmount(productIndex, amountToIncrease);
        this.time = LocalDateTime.now();
        Transaction transaction = createTransaction(pid, amountToIncrease, time);
        transactionList.addTransaction(transaction);
        LOGGER.log(Level.INFO, Messages.MESSAGE_INFLOW_SUCCESS);
    }


    private Transaction createTransaction(Pid pid,
                                      Integer amountToIncrease,
                                      LocalDateTime time) {
        return new Transaction(pid, amountToIncrease, time);
    }
}
