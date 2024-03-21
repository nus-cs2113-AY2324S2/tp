package command;

import item.Item;
import itemlist.Itemlist;

public class EditCommand extends Command {

    protected String itemName;

    protected int newQuantity;

    public EditCommand (String itemName, int newQuantity) {
        this.itemName = itemName;
        this.newQuantity = newQuantity;
    }

    @Override
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
            Itemlist.editQuantity(index, newQuantity);
        }
    }
}
