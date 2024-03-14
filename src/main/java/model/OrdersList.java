package model;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrdersList {
    private ArrayList<Order> ordersList = new ArrayList<>();

    public void add(Order order) {
        ordersList.add(order);
    }

    public void remove(Order order) {
        ordersList.remove(order);
    }

    @Override
    public String toString() {
        return "List of orders:\n" +
                IntStream.range(0, ordersList.size())
                        .mapToObj(x -> (x + 1) + ". " + ordersList.get(x).getID())
                        .collect(Collectors.joining("\n"));
    }
}
