package command;

import model.Order;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;

public class MainViewOrderCommand implements MainCommand{

    public static Optional<Order> execute(ArrayList<Order> ordersList, String inputText) {
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String orderID = indexString[0];
        return ordersList.stream().filter(x -> x.getID().equals(orderID)).findAny();
    }
}
