package command;

import model.Order;

public class OrderExitCommand implements OrderCommand{
    /**
     * Executes the command to exit the order.
     *
     * @param order the current order that will be cancelled
     */
    public static void execute(Order order) {
        System.out.println("Order " + order.getID() + " cancelled");
    }
}
