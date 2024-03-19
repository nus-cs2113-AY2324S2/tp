package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends ListActionCommand {

    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD +
            ": Deletes a product from the inventory with using its PID.\n" +
            "Format: delete PID";
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class.getName());
    protected Pid pid;
    private final Storage storage;



    public DeleteCommand(ProductList productList, Integer pid, Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pid);
        this.storage = storage;
    }

    @Override
    public void execute() throws StockPalException {
        productList.deleteProduct(pid);
        Ui.printDeleteSuccessMessage();
        LOGGER.log(Level.INFO, Messages.MESSAGE_DELETE_SUCCESS);
        storage.save(productList);
    }
}
