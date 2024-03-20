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
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Deletes the specified product from the inventory at the specified PID."
            + Messages.LINE_SEPARATOR
            + "Format: delete PID");
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
