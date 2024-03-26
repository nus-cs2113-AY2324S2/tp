package supertracker.command;

import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.io.IOException;

public class UpdateCommand implements Command {
    private String name;
    private int newQuantity;
    private double newPrice;

    public UpdateCommand(String name, int newQuantity, double newPrice) {
        this.name = name;
        this.newQuantity = newQuantity;
        this.newPrice = newPrice;
    }

    @Override
    public void execute() {
        assert Inventory.contains(name);

        Item oldItem = Inventory.get(name);
        if (newQuantity == -1) {
            newQuantity = oldItem.getQuantity();
        }
        if (newPrice == -1) {
            newPrice = oldItem.getPrice();
        }

        assert newQuantity >= 0;
        assert newPrice >= 0;

        Item newItem = new Item(name, newQuantity, newPrice, oldItem.getExpiryDate());
        Inventory.put(name, newItem);
        Ui.updateCommandSuccess(newItem);

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
