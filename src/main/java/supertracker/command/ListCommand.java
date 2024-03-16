package supertracker.command;

import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.util.Collection;

public class ListCommand implements Command {
    private boolean hasQuantity;
    private boolean hasPrice;
    private String firstParam;

    public ListCommand(boolean hasQuantity, boolean hasPrice, String firstParam) {
        this.hasQuantity = hasQuantity;
        this.hasPrice = hasPrice;
        this.firstParam = firstParam;
    }

    @Override
    public void execute() {
        int index = 1;
        Collection<Item> items = Inventory.items();
        Ui.listIntro(items.size());
        for (Item item : items) {
            Ui.listItem(item, index, hasQuantity, hasPrice, firstParam);
            index++;
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
