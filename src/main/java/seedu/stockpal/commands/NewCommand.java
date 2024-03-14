package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;


public class NewCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    private static Integer pid = 1;
    private final Product toAdd;
    protected ProductList productList;



    public NewCommand(ProductList productList, String name, Integer quantity, Double price, String description) {
        this.toAdd = new Product(name, quantity, price, description, pid++);
        this.productList = productList;
    }

    @Override
    public void execute() {
        productList.addProduct(toAdd);
        System.out.println("Added product!");
    }
}
