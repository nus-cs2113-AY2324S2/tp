package command;

import logic.OrderLogic;
import model.Order;
import java.util.ArrayList;

public class CreateOrderCommand implements MainCommand{

    public static Order execute() {
        return OrderLogic.createNewOrder();
    }
}
