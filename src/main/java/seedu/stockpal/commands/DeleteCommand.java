package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;

public class DeleteCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    protected Pid pid;

    public DeleteCommand(Integer pid, ProductList productList) {
        this.pid = new Pid(pid);
        this.productList = productList;
    }

    public DeleteCommand(ProductList productList, Integer pid) {
        this.productList = productList;
        this.pid = new Pid(pid);

    }
    @Override
    public void execute() {
        productList.deleteProduct(pid);
    }
}
