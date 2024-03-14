package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;

//@@author Kobot7
public class EditCommand extends Command {
    public static final String COMMAND_KEYWORD = "edit";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Edits an existing product in the inventory at the specific PID\n"
            + "Format: edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]";

    ProductList productList;
    Pid pid;
    Name name;
    Quantity quantity;
    Description description;
    Price price;

    public EditCommand(ProductList productList, Pid pid, Name name
            , Quantity quantity, Description description, Price price) {
        this.productList = productList;
        this.pid = pid;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }

    @Override
    public void execute() {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.updateProduct(productIndex, name, quantity, description, price);
    }
}
