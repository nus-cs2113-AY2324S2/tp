package command;

import model.Menu;
import model.MenuItem;
import model.Order;
import ui.Parser;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.*;


public class OrderAddCommand implements OrderCommand{
    private static final Logger logr = Logger.getLogger("OrderAddCommandLogger");
    /**
     * Executes the command to add a specified quantity of an item to an order.
     *
     * @param newOrder    the order to add the item to
     * @param inputText   the string containing details of item to be added
     * @param menu        the menu with the item to be added
     * @return            always returns false, as order is not completed
     */
    public static Order execute(Order newOrder, String inputText, Menu menu) {
        OrderAddCommand.setUpLogger();

        logr.info("Adding new item to order");
        String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
        String itemID = indexString[0];
        String itemQuantity = indexString[1];
        Optional<MenuItem> item = menu.getItem(itemID);
        if (item.isPresent()) {
            for (int i = 0; i < Integer.parseInt(itemQuantity); i++) {
                newOrder.add(item.get());
            }
            logr.info("Item successfully added to order");
            System.out.println(itemQuantity + " " + item.get().getName() + " is added to order");
        } else {
            logr.warning("Item not in menu, not added to order");
            System.out.println("Item not found in menu");
        }
        return newOrder;
    }

    private static void setUpLogger() {
        LogManager.getLogManager().reset();
        logr.setLevel(Level.INFO);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("OrderAddCommandLogger.log");
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (IOException e) {
            logr.log(Level.SEVERE, "File logger not working", e);
        }
    }
}
