package supertracker.command;

import supertracker.item.Inventory;
import supertracker.ui.Ui;

import static supertracker.ui.Ui.deleteUnsuccessful;


public class DeleteCommand implements Command {

    String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        if (!Inventory.contains(itemName)) {
            deleteUnsuccessful();
            return;
        }

        Inventory.delete(itemName);
        Ui.deleteSuccess(itemName);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
