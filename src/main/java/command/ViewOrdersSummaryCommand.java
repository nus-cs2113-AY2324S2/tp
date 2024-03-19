package command;

import model.Order;

import java.util.ArrayList;

public class ViewOrdersSummaryCommand implements MainCommand{
    public static void execute(ArrayList<Order> ordersList) {
        ordersList.forEach(x -> System.out.println(x.getOrderSummary()));
    }
}
