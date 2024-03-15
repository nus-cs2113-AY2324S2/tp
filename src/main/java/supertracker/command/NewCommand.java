package supertracker.command;

import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

public class NewCommand implements Command {
    private String name;
    private int quantity;
    private double price;

    public NewCommand(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public void execute() {
        Item item = new Item(name, quantity, price);
        Inventory.put(name, item);
        Ui.newCommandSuccess(item);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
