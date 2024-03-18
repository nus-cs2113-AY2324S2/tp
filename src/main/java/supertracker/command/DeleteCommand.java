package supertracker.command;

import supertracker.item.Inventory;
import supertracker.ui.Ui;

public class DeleteCommand implements Command {

    private String name;

    public DeleteCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        Inventory.delete(name);
        Ui.deleteCommandSuccess(name);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
