package command;

import exceptions.CommandFormatException;
import item.Item;
import itemlist.Itemlist;
import parser.CommandType;
import storage.Storage;

public class DeleteCommand extends Command {

    protected String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName;
    }

    public void execute() throws CommandFormatException {
        int index = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                break;
            }
        }
        if (index == -1) {
            //throw exception;
            throw new CommandFormatException(CommandType.DEL);
        } else {
            Itemlist.deleteItem(index);
            System.out.println(itemName + " has been successfully deleted.");
            Storage.overwriteFile(Itemlist.getItems(), false);
            assert(!Itemlist.getItem(index).getItemName().equals(itemName));
        }
    }
}
