package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;

public class OutflowCommand extends Command {
    public static final String COMMAND_KEYWORD = "outflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    ProductList productList;
    Pid pid;
    Integer amountToDecrease;

    public OutflowCommand(ProductList productList, Integer pidValue, Integer amountToDecrease) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToDecrease = amountToDecrease;
    }

    @Override
    public void execute() {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.decreaseAmount(productIndex, amountToDecrease);
    }
}
