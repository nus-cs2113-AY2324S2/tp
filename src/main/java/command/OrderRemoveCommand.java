package command;

import model.Order;

public class OrderRemoveCommand implements OrderCommand {
    /**
     * Executes the command to remove an item from an order.
     *
     * @param order     the order containing the item to be removed
     * @param itemId    the id of the item as indicated in the menu
     * @param quantity  the amount of the specified item to remove
     * @return          always returns false, as order is not completed
     */
    public static boolean execute(Order order, String itemId, int quantity) {
        for (int i = 0; i < quantity; i ++) {
            order.remove(itemId);
        }
        return false;
    }
}
