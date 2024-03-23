package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends ListActionCommand {

    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_DESCRIPTION = "Deletes an existing product from the inventory.";
    public static final String COMMAND_USAGE = "Format: delete PID";

    public static final String[] COMMAND_FLAGS = {
        "PID"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
    };
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class.getName());
    private final Pid pid;

    public DeleteCommand(Integer pid) {
        this.pid = new Pid(pid);
    }

    @Override
    public void execute(ProductList productList) throws StockPalException {
        productList.deleteProduct(pid);
        Ui.printDeleteSuccessMessage();
        LOGGER.log(Level.INFO, Messages.MESSAGE_DELETE_SUCCESS);
    }
}
