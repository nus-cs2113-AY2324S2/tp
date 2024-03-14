package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    private final ProductList products;

    public ListCommand(ProductList products) {
        this.products = products;
    }

    @Override
    public void execute() {
        if (products.products.isEmpty()) {
            System.out.println("ProductList is empty");
            return;
        }

        Ui.printListTasks(products.products);
    }

}
