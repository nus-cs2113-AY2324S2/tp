package command;

import model.Order;

public class ViewItemsCommand implements OrderCommand{
    /**
     * Executes the command to list all the items in an order.
     *
     * @param order     the order to be listed
     * @return          always returns false, as order is not completed
     */
    public static boolean execute(Order order) {
        System.out.println(order.toString());
        return false;
    }
}
