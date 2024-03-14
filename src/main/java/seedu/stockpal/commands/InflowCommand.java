package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Quantity;

public class InflowCommand extends Command {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    ProductList productList;
    Pid pid;
    Integer amountToIncrease;

    public InflowCommand(ProductList productList, Pid pid, Integer amountToIncrease) {
        this.productList = productList;
        this.pid = pid;
        this.amountToIncrease = amountToIncrease;
    }


    @Override
    public void execute() {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.increaseAmount(productIndex, amountToIncrease);

    }
}
