package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class DeleteCommand extends Command {

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
        } else {
            Itemlist.deleteItem(index);
        }
        System.out.println(itemName + " has been successfully deleted.");
        Storage.overwriteFile(Itemlist.getItems(), false);
    }
}
