package command;

import item.Item;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "added: ";
    protected String itemName;
    protected int quantity;
    protected String uom;
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String uom) {
        this.toAdd = new Item(itemName, quantity, uom);
    }

    public String getItemNam() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUom() {
        return uom;
    }
    @Override
    public void execute() {
        //itemList.add(toAdd);
        System.out.println(MESSAGE_SUCCESS + getItemNam() + "(Qty: " + getQuantity() + ")");
    }
}
