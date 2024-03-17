package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

import static seedu.stockpal.common.Messages.MESSAGE_ADDED;
import static seedu.stockpal.ui.Ui.printToScreen;


public class NewCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
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
        printToScreen(MESSAGE_ADDED);
        storage.append(toAdd);
    }
}
