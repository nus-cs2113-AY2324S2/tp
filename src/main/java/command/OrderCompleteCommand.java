package command;

import model.Order;

public class OrderCompleteCommand implements OrderCommand{
    /**
     * Executes the command to complete an order.
     *
     * @param order     the order to be completed
     * @return true if the order is completed, false otherwise
     */
    public static boolean execute(Order order) {
        if (order.getSize() == 0) {
            System.out.println("Order " + order.getID() + " is empty. Please add items to the order.");
            return false;
        }
        return true;
    }
}
