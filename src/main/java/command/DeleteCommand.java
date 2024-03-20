package command;

import item.Item;
import itemlist.Itemlist;

public class DeleteCommand {

    protected String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName;
    }

    public void execute() {
        int index = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                break;
            }
        }
        if (index == -1) {
            //throw exception;
        }
        else {
            Itemlist.deleteItem(index);
        }
    }
}
