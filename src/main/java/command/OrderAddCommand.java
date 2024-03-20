package command;

import model.MenuItem;
import model.Order;

public class OrderAddCommand implements OrderCommand{
    /**
     * Executes the command to add a specified quantity of an item to an order.
     *
     * @param order     the order to which the item should be added
     * @param item      the item to be added to the Order
     * @param quantity  the amount of items to add to the order
     * @return          always returns false, as order is not completed
     */
    public boolean execute(Order order, MenuItem item, int quantity) {
        for (int i = 0; i < quantity; i ++) {
            order.add(item);
        }
        return false;
    }
}
