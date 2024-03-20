package command;

import model.Order;

import java.util.ArrayList;

public class MainViewOrdersSummaryCommand implements MainCommand{
    public static void execute(ArrayList<Order> ordersList) {
        if (ordersList.isEmpty()){
            System.out.println("No orders available");
            return;
        }
        ordersList.forEach(x -> System.out.println(x.getOrderSummary()));
    }
}
