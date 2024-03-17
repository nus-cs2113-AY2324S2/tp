package seedu.stockpal.commands;

import seedu.stockpal.common.CommandParameter;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.ui.Ui;

import java.util.Arrays;

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

    public EditCommand(ProductList productList, Integer pid, String name,
                       Integer quantity, Double price, String description) {
        this.productList = productList;
        this.pid = new Pid(pid);
        this.name = new Name(name);
        this.quantity = new Quantity(quantity);
        this.price = new Price(price);
        this.description = new Description(description);
    }

    private void checkAtLeastOneValidArgument() throws InvalidCommandException {
        for (CommandParameter parameter : Arrays.asList(name, quantity, price, description)) {
            if (!parameter.isNull()) {
                return;
            }
        }

        throw new InvalidCommandException(Messages.MESSAGE_ERROR_MISSING_PARAMETERS);
    }
    @Override
    public void execute() {
        try {
            checkAtLeastOneValidArgument();
        } catch (InvalidCommandException exception) {
            Ui.printExceptionMessage(exception);
            return;
        }

        int productIndex = this.productList.findProductIndex(this.pid);
        productList.updateProduct(productIndex, name, quantity, description, price);
        Ui.printEditSuccessMessage();
    }
}
