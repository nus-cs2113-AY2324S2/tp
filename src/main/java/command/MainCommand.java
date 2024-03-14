package command;

import model.Order;
import model.OrdersList;

public interface MainCommand {

    void execute(OrdersList ordersList, Order order);
    boolean isExit();
}
