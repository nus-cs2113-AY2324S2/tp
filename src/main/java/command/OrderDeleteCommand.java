package command;

import model.Menu;
import model.Order;
import ui.Parser;


public class OrderDeleteCommand implements OrderCommand {
    /**
     * Executes the command to remove an item from an order.
     *
     * @param order     the order containing the item to be removed
     * @param inputText the input containing the details of the item to be removed
     * @param menu      the menu that the item to be removed is from
     * @return          always returns false, as order is not completed
     */
    public static Order execute(Order order, String inputText, Menu menu) {
        Order newOrder = order;
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemQuantity = indexString[1];
        if (newOrder.getItemCount(itemID) == 0) {
            System.out.println("Item not found in order");
            return newOrder;
        } else if (newOrder.getItemCount(itemID) - Integer.parseInt(itemQuantity) <= 0) {
            newOrder.remove(itemID);
            assert newOrder.getItemCount(itemID) == 0 : "all items of itemID should be removed from the order";
            System.out.println("All " + " " + menu.getItem(itemID).get().getName() + " is removed from order");
            return newOrder;
        } else {
            for (int i = 0; i < Integer.parseInt(itemQuantity); i++) {
                newOrder.remove(menu.getItem(itemID).get());
            }
            System.out.println(itemQuantity + " " + menu.getItem(itemID).get().getName() + " is removed from order");
        }
        return newOrder;
    }
}
