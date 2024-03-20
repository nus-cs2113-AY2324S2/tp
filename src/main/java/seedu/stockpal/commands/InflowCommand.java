package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Increase quantity of an existing product in the inventory at the specific PID\n"
            + "Format: inflow PID [a/QUANTITY] ";

    private static Logger logger = Logger.getLogger(InflowCommand.class.getName());

    private ProductList productList;
    private Pid pid;
    private Integer amountToIncrease;
    private final Storage storage;

    public InflowCommand(ProductList productList, Integer pidValue, Integer amountToIncrease, Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToIncrease = amountToIncrease;
        this.storage = storage;
    }

    @Override
    public void execute() throws StockPalException {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.increaseAmount(productIndex, amountToIncrease);
        logger.log(Level.INFO, Messages.MESSAGE_INFLOW_SUCCESS);
        storage.save(productList);
    }
}
