package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;

//@@author Kobot7
public class EditCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "edit";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Edits an existing product in the inventory at the specific PID\n"
            + "Format: edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]";

    Pid pid;
    Name name;
    Quantity quantity;
    Description description;
    Price price;
    private final Storage storage;

    public EditCommand(ProductList productList, Integer pid, String name,
                       Integer quantity, Double price, String description,
                       Storage storage) {
        this.productList = productList;
        this.pid = new Pid(pid);
        this.name = new Name(name);
        this.quantity = new Quantity(quantity);
        this.price = new Price(price);
        this.description = new Description(description);
        this.storage = storage;
    }
    
    @Override
    public void execute() throws StockPalException {
        int productIndex = this.productList.findProductIndex(this.pid);
        productList.updateProduct(productIndex, name, quantity, description, price);
        storage.save(productList);
    }
}
