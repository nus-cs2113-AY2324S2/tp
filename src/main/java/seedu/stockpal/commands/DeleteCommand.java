package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

public class DeleteCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD +
            ": Deletes a product from the inventory with using its PID.\n" +
            "Format: delete PID";
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
        storage.save(productList);
    }
}
