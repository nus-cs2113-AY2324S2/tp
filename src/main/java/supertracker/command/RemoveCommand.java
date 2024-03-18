package supertracker.command;

import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.ui.Ui;

public class RemoveCommand implements Command {
    private String name;
    private int quantity;

    public RemoveCommand(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        Item oldItem = Inventory.get(name);
        int newQuantity = oldItem.getQuantity() - quantity;
        newQuantity = Math.max(newQuantity, 0);
        int quantityRemoved = oldItem.getQuantity() - newQuantity;
        Item newItem = new Item(name, newQuantity, oldItem.getPrice());
        Inventory.put(name, newItem);
        Ui.removeCommandSuccess(newItem, quantityRemoved);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
