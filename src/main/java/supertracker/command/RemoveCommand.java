package supertracker.command;

import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;

import java.io.IOException;

public class RemoveCommand implements Command {
    private String name;
    private int quantity;

    public RemoveCommand(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        assert Inventory.contains(name);
        assert quantity >= 0;

        Item oldItem = Inventory.get(name);
        int newQuantity = oldItem.getQuantity() - quantity;
        newQuantity = Math.max(newQuantity, 0);
        int quantityRemoved = oldItem.getQuantity() - newQuantity;
        Item newItem = new Item(name, newQuantity, oldItem.getPrice(), oldItem.getExpiryDate());
        Inventory.put(name, newItem);
        Ui.removeCommandSuccess(newItem, quantityRemoved);

        try {
            FileManager.saveData();
        } catch (IOException e) {
            Ui.printError(ErrorMessage.FILE_SAVE_ERROR);
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
