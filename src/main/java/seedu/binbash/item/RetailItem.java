package seedu.binbash.item;

public class RetailItem extends Item {
    private final double itemSalePrice;
    public RetailItem(String itemName, String itemDescription, int itemQuantity,
                      double itemSalePrice, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice);
        this.itemSalePrice = itemSalePrice;
    }

    public double getItemSalePrice() {
        return itemSalePrice;
    }

    @Override
    public String toString() {
        return "[R] " + super.toString() + System.lineSeparator()
                + String.format("\tsale price: $%.2f", itemSalePrice);
    }
}
