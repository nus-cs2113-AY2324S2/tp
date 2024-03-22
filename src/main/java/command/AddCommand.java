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
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String uom, String category) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.uom = uom;
        this.category = category;
        this.toAdd = new Item(itemName, quantity, uom, category);
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

    @Override
    public void execute() {
        Itemlist.addItem(toAdd);
        System.out.print(MESSAGE_SUCCESS + getItemName() + " (Qty: " + getQuantity() + " " + getUom() + ")");
        Storage.addToFile(Itemlist.getItems(), true);
        if (!category.equals("NA")) {
            System.out.println(" to " + getCategory());
        } else {
            System.out.println();
            assert category.equals("NA");
        }
    }
}
