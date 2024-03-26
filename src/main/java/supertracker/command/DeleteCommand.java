package supertracker.command;

import supertracker.item.Inventory;
import supertracker.storage.FileManager;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;

import java.io.IOException;

public class DeleteCommand implements Command {

    private String name;

    public DeleteCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        assert Inventory.contains(name);

        Inventory.delete(name);
        Ui.deleteCommandSuccess(name);

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
