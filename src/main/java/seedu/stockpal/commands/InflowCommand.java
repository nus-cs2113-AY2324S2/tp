package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Increases the quantity of product with PID "
            + "by the specified INCREMENT_AMOUNT from the existing amount."
            + Messages.LINE_SEPARATOR
            + "Format: inflow PID a/INCREMENT_AMOUNT");

    private static final Logger LOGGER = Logger.getLogger(InflowCommand.class.getName());

    private final Pid pid;
    private final Integer amountToIncrease;

    public InflowCommand(Integer pidValue, Integer amountToIncrease) {
        this.pid = new Pid(pidValue);
        this.amountToIncrease = amountToIncrease;
    }

    @Override
    public void execute(ProductList productList) throws StockPalException {
        int productIndex = productList.findProductIndex(this.pid);
        productList.increaseAmount(productIndex, amountToIncrease);
        LOGGER.log(Level.INFO, Messages.MESSAGE_INFLOW_SUCCESS);
    }
}
