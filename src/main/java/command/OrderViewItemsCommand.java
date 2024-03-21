package command;

import model.Order;

public class OrderViewItemsCommand implements OrderCommand{
    /**
     * Executes the command to list all the items in an order.
     *
     * @param order     the order to be listed
     */
    public static void execute(Order order) {
        System.out.println("Order:" + order.toString());
        if (order.getSize() == 0) {
            System.out.println("Order is empty.");
        }
    }
}
