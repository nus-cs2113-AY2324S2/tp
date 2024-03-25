package seedu.binbash.item;

public class OperationalItem extends Item {
    public OperationalItem(String itemName, String itemDescription, int itemQuantity, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice);
    }

    @Override
    public String toString() {
        return "[O] " + super.toString();
    }
}
