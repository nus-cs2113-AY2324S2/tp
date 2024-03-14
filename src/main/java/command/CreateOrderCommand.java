package command;

import model.Order;
import model.OrdersList;

public class CreateOrderCommand implements MainCommand{
    private boolean isExit;

    @Override
    public void execute(OrdersList ordersList, Order order) {
        isExit = false;
        Order newOrder = new Order();
        ordersList.add(newOrder);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
