package supertracker.command;

import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.ui.Ui;

import java.util.List;

public class FindCommand implements Command {
    private String name;

    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        int index = 1;
        List<Item> items = Inventory.items();
        Ui.findIntro();
        for (Item item : items) {
            if(item.getName().contains(name)){
                Ui.findItem(item, index);
                index++;
            }
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
