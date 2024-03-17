package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.ui.Ui;

public class InflowCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "inflow";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Edits an existing product in the inventory at the specific PID\n"
            + "Format: edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]";

    ProductList productList;
    Pid pid;
    Integer amountToIncrease;

    public InflowCommand(ProductList productList, Integer pidValue, Integer amountToIncrease) {
        this.productList = productList;
        this.pid = new Pid(pidValue);
        this.amountToIncrease = amountToIncrease;
    }

    @Override
    public void execute() {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.increaseAmount(productIndex, amountToIncrease);
        Ui.printToScreen("Quantity updated! New quantity is: " + productList.getProductQuantity(productIndex));
    }
}
