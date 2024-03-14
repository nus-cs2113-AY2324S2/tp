package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    @Override
    public void execute() {
        if (ProductList.products.isEmpty()) {
            System.out.println("ProductList is empty");
            return;
        }

        Ui.printListTasks(ProductList.products);
    }

}
