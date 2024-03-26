package supertracker.command;

import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.io.IOException;
import java.time.LocalDate;

public class NewCommand implements Command {
    private String name;
    private int quantity;
    private double price;

    private LocalDate expiryDate;

    public NewCommand(String name, int quantity, double price, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    @Override
    public void execute() {
        assert !Inventory.contains(name);
        assert quantity >= 0;
        assert price >= 0;

        Item item = new Item(name, quantity, price, expiryDate);
        Inventory.put(name, item);
        Ui.newCommandSuccess(item);

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
