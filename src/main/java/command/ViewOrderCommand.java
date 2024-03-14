package command;

import model.Order;
import model.OrdersList;

public class ViewOrderCommand implements MainCommand{
    private boolean isExit;
    @Override
    public void execute(OrdersList ordersList, Order order) {
        isExit = false;
        System.out.println(order.toString());
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
