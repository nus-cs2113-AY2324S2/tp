package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;

public class DeleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    Pid pid;
    ProductList productList;

    public DeleteCommand(Integer pid, ProductList productList) {
        this.pid = new Pid(pid);
        this.productList = productList;
    }

    @Override
    public void execute() {
        productList.deleteProduct(pid);
    }

}
