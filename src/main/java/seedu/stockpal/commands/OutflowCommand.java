package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OutflowCommand extends ListActionCommand {
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
    private final Integer amountToDecrease;

    public OutflowCommand(Integer pidValue, Integer amountToDecrease) {
        this.pid = new Pid(pidValue);
        this.amountToDecrease = amountToDecrease;
    }

    @Override
    public void execute(ProductList productList) throws StockPalException {
        int productIndex = productList.findProductIndex(this.pid);
        if (productIndex == -1) {
            Ui.printInvalidPidMessage();
            return;
        }
        productList.decreaseAmount(productIndex, amountToDecrease);
        LOGGER.log(Level.INFO, Messages.MESSAGE_OUTFLOW_SUCCESS);
    }
}
