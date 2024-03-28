package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;

import java.util.logging.Level;

import static seedu.stockpal.commands.EditCommand.logger;
import static seedu.stockpal.ui.Ui.printToScreen;

//@@author EdmundTangg
public class NewCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_DESCRIPTION =
        "Adds a new product to the inventory and assigns a unique Product" +
                "\nID (PID) to it.";
    public static final String COMMAND_USAGE =
        "new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]";

    public static final String[] COMMAND_FLAGS = {
        "PRODUCT_NAME"
        , "INITIAL_QUANTITY"
        , "PRICE"
        , "DESCRIPTION"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Name of the product"
        , "Quantity of the product"
        , "Price of the product. Price must be in 2 decimal places."
        , "Description of the product"
    };

    private final String name;
    private final Integer quantity;
    private final Double price;
    private final String description;

    public NewCommand(String name,
                      Integer quantity,
                      Double price,
                      String description) {
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

    /**
     * Creates a product.
     *
     * @param productList List of products object.
     * @param name Name of object.
     * @param quantity Quantity of object.
     * @param price Price of object.
     * @param description Description of object.
     * @return object created.
     */
    private Product createProduct(ProductList productList,
                                  String name,
                                  Integer quantity,
                                  Double price,
                                  String description) {

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
