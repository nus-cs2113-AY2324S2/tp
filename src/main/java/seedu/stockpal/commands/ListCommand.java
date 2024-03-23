package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.stockpal.ui.Ui.printToScreen;

public class ListCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_DESCRIPTION = "Lists each product in inventory.";
    public static final String COMMAND_USAGE = "list";
    public static final String[] COMMAND_FLAGS = {};
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {};
    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());

    /**
     * Prints out all products that are in the list.
     * If the list is empty, it tells the user that the list is empty.
     */
    @Override
    public void execute(ProductList productList) {
        if (productList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_LIST);
            return;
        }

        Ui.printListTasks(productList);
        LOGGER.log(Level.INFO, Messages.MESSAGE_LIST_SUCCESS);
    }

}
