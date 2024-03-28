package command;

import item.Item;
import itemlist.Itemlist;
import ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command{

    protected String itemName;

    public FindCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        ArrayList<String> searchList = new ArrayList<>();
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().contains(itemName)) {
                searchList.add(String.valueOf(item));
            }
        }
        TextUi.showInventoryList(searchList);
    }
}
