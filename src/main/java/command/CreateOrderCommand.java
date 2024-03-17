package command;

import logic.OrderLogic;
import model.Order;

public class CreateOrderCommand implements MainCommand{

    public static Order execute() {
        return OrderLogic.createNewOrder();
    }
}
