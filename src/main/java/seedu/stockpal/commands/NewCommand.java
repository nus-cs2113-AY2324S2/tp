package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;

import static seedu.stockpal.commands.EditCommand.logger;
import static seedu.stockpal.ui.Ui.printToScreen;

public class NewCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Creates a new product to the inventory and assigns a unique Product ID (PID) to it."
            + Messages.LINE_SEPARATOR
            + "Format: new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]")
            + Messages.LINE_SEPARATOR
            + "PRICE must be in 2 decimal places.";

    private final String name;
    private final Integer quantity;
    private final Double price;
    private final String description;

    public NewCommand(String name, Integer quantity, Double price, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    @Override
    public void execute(ProductList productList) throws StockPalException {
        Product toAdd = createProduct(productList, this.name, this.quantity, this.price, this.description);
        productList.addProduct(toAdd);
        printToScreen(Messages.MESSAGE_ADDED);

        if (productList.getSize() < 0) {
            throw new AssertionError();
        }
        logger.log(Level.INFO, Messages.MESSAGE_ADDED);
    }

    private Product createProduct(ProductList productList, String name, Integer quantity,
                                  Double price, String description) {
        int sizeOfArray = productList.getSize();
        int pid;

        if (sizeOfArray == 0) {
            pid = 1;
        } else if (sizeOfArray == productList.get(sizeOfArray - 1).getPid().getPid()) {
            pid = sizeOfArray + 1;
        } else {
            pid = productList.get(sizeOfArray - 1).getPid().getPid() + 1;
        }
        return new Product(name, quantity, price, description, pid);
    }
}
