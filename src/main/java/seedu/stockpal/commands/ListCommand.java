package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.stockpal.common.Messages.MESSAGE_EMPTY_LIST;
import static seedu.stockpal.common.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.stockpal.ui.Ui.printToScreen;

public class ListCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());
    protected ProductList productList;

    public ListCommand(ProductList productList) {
        this.productList = productList;
    }

    /**
     * Prints out all products that are in the list.
     * If the list is empty, it tells the user that the list is empty.
     */
    @Override
    public void execute() {
        if (productList.isEmpty()) {
            printToScreen(MESSAGE_EMPTY_LIST);
            return;
        }

        Ui.printListTasks(productList);
        LOGGER.log(Level.INFO, MESSAGE_LIST_SUCCESS);
    }

}
