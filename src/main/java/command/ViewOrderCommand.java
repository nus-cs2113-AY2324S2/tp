package command;

import model.Order;
import ui.Parser;

import java.util.ArrayList;

public class ViewOrderCommand implements MainCommand{

    public static void execute(ArrayList<Order> ordersList, String inputText) {
        try {
            String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
            String orderID = indexString[0];
            ordersList.stream().filter(x -> x.getID().equals(orderID)).forEach(System.out::println);
        } catch (IndexOutOfBoundsException e) {
            //TODO: add exception class
            System.out.println("Order does not exist");
        }
    }
}
