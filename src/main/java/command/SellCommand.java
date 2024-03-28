package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;


public class SellCommand extends Command {

    protected String itemName;
    protected int sellQuantity;
    protected int sellPrice;

    public SellCommand (String itemName, int quantity, int price) {
        this.itemName = itemName;
        this.sellQuantity = quantity;
        this.sellPrice = price;
    }

    @Override
    public void execute() {
        int index = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().equals(itemName) || item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                item = Itemlist.getItems().get(index);
                break;
            }
        }
        int remainingQuantity = Itemlist.getItem(index).getQuantity() - sellQuantity;
        int sellPrice = (this.sellPrice >= 0) ? this.sellPrice : Itemlist.getItem(index).getSellPrice();
        if (index == -1) {
            //throw exception;
            System.out.println("Item not found!");
        } else if (remainingQuantity < 0) {
            System.out.println("There is insufficient stock!");
        } else {
            ui.TextUi.showSellMessage(itemName, sellQuantity, remainingQuantity, sellPrice);
            Itemlist.editQuantity(index, remainingQuantity);
        }
        Storage.overwriteFile(Itemlist.getItems());
    }
}
