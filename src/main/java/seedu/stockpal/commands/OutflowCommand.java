package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

public class OutflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "outflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    private ProductList productList;
    private Pid pid;
    private Integer amountToDecrease;
    private final Storage storage;

    public OutflowCommand(ProductList productList, Integer pidValue, Integer amountToDecrease, Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToDecrease = amountToDecrease;
        this.storage = storage;
    }
    @Override
    public void execute() throws StockPalException {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.decreaseAmount(productIndex, amountToDecrease);
        storage.save(productList);
    }
}
