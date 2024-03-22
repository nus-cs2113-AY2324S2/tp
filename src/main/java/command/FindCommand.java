package command;

import item.Item;
import itemlist.Itemlist;

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
                int index = Itemlist.getItems().indexOf(item);
                String itemNameWithIndex = index + ". " + item.getItemName();
                searchList.add(itemNameWithIndex);
            }
        }
        //displayList(searchList);
    }
}
