package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OutflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "outflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Reduce quantity of an existing product in the inventory at the specific PID\n"
            + "Format: outflow PID [a/QUANTITY] ";

    private static Logger logger = Logger.getLogger(OutflowCommand.class.getName());

    private ProductList productList;
    private Pid pid;
    private Integer amountToDecrease;
    private final Storage storage;

    public OutflowCommand(ProductList productList, Integer pidValue, Integer amountToDecrease, Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToDecrease = amountToDecrease;
        this.storage = storage;
    }

    @Override
    public void execute() throws StockPalException {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.decreaseAmount(productIndex, amountToDecrease);
        logger.log(Level.INFO, Messages.MESSAGE_OUTFLOW_SUCCESS);
        storage.save(productList);
    }
}
