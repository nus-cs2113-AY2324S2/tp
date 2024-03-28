package supertracker.command;

import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;

import java.io.IOException;

public class AddCommand implements Command {
    private String name;
    private int quantity;

    public AddCommand(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        assert Inventory.contains(name);
        assert quantity >= 0;

        Item oldItem = Inventory.get(name);
        int newQuantity = oldItem.getQuantity() + quantity;
        Item newItem = new Item(name, newQuantity, oldItem.getPrice(), oldItem.getExpiryDate());
        Inventory.put(name, newItem);
        Ui.addCommandSuccess(newItem, quantity);

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
