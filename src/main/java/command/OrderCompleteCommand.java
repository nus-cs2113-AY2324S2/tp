package command;

import model.Order;

public class OrderCompleteCommand implements OrderCommand{
    /**
     * Executes the command to complete an order.
     *
     * @param order     the order to be completed
     * @return          always returns true, as order is completed
     */
    public boolean execute(Order order) {
        order.setComplete();
        return true;
    }
}
