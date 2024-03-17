package supertracker.command;

import supertracker.item.Inventory;

public class DeleteCommand implements Command {

    String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        Inventory.delete(itemName);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
