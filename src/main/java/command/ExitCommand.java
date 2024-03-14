package command;

import model.Order;
import model.OrdersList;

public class ExitCommand implements MainCommand{
    private boolean isExit;
    @Override
    public void execute(OrdersList ordersList, Order order) {
        isExit = true;
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
