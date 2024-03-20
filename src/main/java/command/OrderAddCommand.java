package command;

import model.Menu;
import model.MenuItem;
import model.Order;
import ui.Parser;

import java.util.Optional;


public class OrderAddCommand implements OrderCommand{
    /**
     * Executes the command to add a specified quantity of an item to an order.
     *
     * @param inputText    the order to add the item to
     * @return          always returns false, as order is not completed
     */
    public static Order execute(Order newOrder, String inputText, Menu menu) {

        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemQuantity = indexString[1];
        Optional<MenuItem> item = menu.getItem(itemID);
        if (item.isPresent()) {
            for (int i = 0; i < Integer.parseInt(itemQuantity); i++) {
                newOrder.add(item.get());
            }
            System.out.println(itemQuantity + " " + item.get().getName() + " is added to order");
        } else {
            System.out.println("Item not found in menu");
        }
        return newOrder;
    }
}
