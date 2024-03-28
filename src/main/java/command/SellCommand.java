package command;

import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import storage.Storage;
import storage.TransactionLogs;


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
        Item toSell = Itemlist.getItems().get(0);
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().equals(itemName) || item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                toSell = item;
                break;
            }
        }
        int remainingQuantity = Itemlist.getItem(index).getQuantity() - sellQuantity;
        int sellPrice = (this.sellPrice >= 0) ? this.sellPrice : Itemlist.getItem(index).getSellPrice();
        if (index == -1) {
            //throw exception;
            System.out.println("Item not found!");
            return;
        } else if (remainingQuantity < 0) {
            System.out.println("There is insufficient stock!");
            return;
        } else {
            ui.TextUi.showSellMessage(itemName, sellQuantity, remainingQuantity, sellPrice);
            Itemlist.editQuantity(index, remainingQuantity);
        }
        Storage.overwriteFile(Itemlist.getItems());
        Transaction newTransaction = new Transaction(itemName, sellQuantity, toSell.getBuyPrice(), sellPrice);
        Cashier.addItem(newTransaction);
        TransactionLogs.addToLog(Cashier.getTransactions());
    }
}
