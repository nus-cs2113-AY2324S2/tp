package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

public class InflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Increase quantity of an existing product in the inventory at the specific PID\n"
            + "Format: inflow PID [a/QUANTITY] ";

    private ProductList productList;
    private Pid pid;
    private Integer amountToIncrease;
    private final Storage storage;

    public InflowCommand(ProductList productList, Integer pidValue, Integer amountToIncrease, Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToIncrease = amountToIncrease;
        this.storage = storage;
    }

    @Override
    public void execute() throws StockPalException {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.increaseAmount(productIndex, amountToIncrease);
        storage.save(productList);
    }
}
