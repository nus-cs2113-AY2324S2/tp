package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;

import static seedu.stockpal.commands.EditCommand.logger;
import static seedu.stockpal.ui.Ui.printToScreen;


public class NewCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Creates a new product to the inventory and assigns a unique Product ID (PID) to it."
            + Messages.LINE_SEPARATOR
            + "Format: new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]");

    protected ProductList productList;
    private final Product toAdd;
    private final Storage storage;


    public NewCommand(ProductList productList, String name, Integer quantity,
                      Double price, String description, Storage storage) {
        int sizeOfArray = productList.getSize();
        int pid;

        if (sizeOfArray == 0) {
            pid = 1;
        } else if (sizeOfArray == productList.get(sizeOfArray - 1).getPid().getPid()) {
            pid = sizeOfArray + 1;
        } else {
            pid = productList.get(sizeOfArray - 1).getPid().getPid() + 1;
        }
        this.toAdd = new Product(name, quantity, price, description, pid);
        this.productList = productList;
        this.storage = storage;
    }

    @Override
    public void execute() throws StockPalException {
        productList.addProduct(toAdd);
        printToScreen(Messages.MESSAGE_ADDED);
        storage.append(toAdd);

        if (productList.getSize() < 0) {
            throw new AssertionError();
        }
        logger.log(Level.INFO, Messages.MESSAGE_ADDED);
    }
}
