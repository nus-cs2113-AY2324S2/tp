package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "added: ";
    protected String itemName;
    protected int quantity;
    protected String uom;
    protected String category = "NA";
    protected int buyPrice;
    protected int sellPrice;
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String uom, String category, int buyPrice, int sellPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.uom = uom;
        this.category = category;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.toAdd = new Item(itemName, quantity, uom, category, buyPrice, sellPrice);
    }

    public String getItemName() {
        return itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
    public String getUom() {
        return uom;
    }
    public int getBuyPrice() {
        return buyPrice;
    }
    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public void execute() {
        if (Itemlist.itemIsExist(itemName)) {
            System.out.println("Item already exists. To increase the quantity, please use the edit command");
        } else {
            Itemlist.addItem(toAdd);
            System.out.print(MESSAGE_SUCCESS + getItemName() + " (Qty: " + getQuantity() + getUom() +
                    ", Buy: $" + getBuyPrice() + ", Sell: $" + getSellPrice() + ")");
            Storage.addToFile(Itemlist.getItems());
            if (!category.equals("NA")) {
                System.out.println(" to " + getCategory());
            } else {
                System.out.println();
                assert category.equals("NA");
            }
        }
    }
}
