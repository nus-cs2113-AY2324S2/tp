package command;

import model.Order;
import model.OrdersList;

public class ViewAllOrdersCommand implements MainCommand{
    private boolean isExit;

    @Override
    public void execute(OrdersList ordersList, Order order) {
        System.out.println(ordersList.toString());
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
