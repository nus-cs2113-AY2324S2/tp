package supertracker.command;

import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.util.Collection;

public class ListCommand implements Command {
    private boolean qExists;
    private boolean pExists;
    private String firstParam;

    public ListCommand(boolean qExists, boolean pExists, String firstParam) {
        this.qExists = qExists;
        this.pExists = pExists;
        this.firstParam = firstParam;
    }

    @Override
    public void execute() {
        int index = 1;
        Collection<Item> items = Inventory.items();
        Ui.listIntro(items.size());
        for (Item item : items) {
            Ui.listItem(item, index, qExists, pExists, firstParam);
            index++;
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
