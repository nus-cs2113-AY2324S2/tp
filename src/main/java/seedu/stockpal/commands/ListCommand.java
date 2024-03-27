package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;
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

    private final String sortType;

    public ListCommand(String sortType) {
        this.sortType = sortType;
    }

    /**
     * Prints out all products that are in the list.
     * It sorts the products to either the name, quantity or by default, it's PID.
     * If the list is empty, it tells the user that the list is empty.
     */
    @Override
    public void execute(ProductList productList) {
        if (productList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_LIST);
            return;
        }

        ProductList newList = new ProductList();
        newList.products = new ArrayList<>(productList.getProducts());
        sortListAccordingly(newList);

        Ui.printListTasks(newList);
        LOGGER.log(Level.INFO, Messages.MESSAGE_LIST_SUCCESS);
    }

    /**
     * Sorts a new product list accordingly to either quantity or name.
     * This is to prevent modification to the actual list, when it's sorted.
     *
     * @param newList is a copy of the original list.
     */
    private void sortListAccordingly(ProductList newList) {
        if (sortType != null) {
            switch (sortType) {
            case "-sq":
                newList.products.sort(new SortByQuantity());
                break;
            case "-sn":
                newList.products.sort(new SortByName());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Creates a comparator that sorts a product by quantity.
     */
    private static class SortByQuantity implements Comparator<Product> {
        @Override
        public int compare(Product a, Product b) {
            return a.getQuantity().getQuantity() - b.getQuantity().getQuantity();
        }
    }

    /**
     * Creates a comparator that sorts a product by name.
     */
    private static class SortByName implements Comparator<Product> {
        @Override
        public int compare(Product a, Product b) {
            return a.getName().getName().toLowerCase().
                    compareTo(b.getName().getName().toLowerCase());
        }
    }
}
