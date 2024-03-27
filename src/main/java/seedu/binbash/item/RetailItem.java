package seedu.binbash.item;

public class RetailItem extends Item {
    private final double itemSalePrice;
    private int totalUnitsSold;
    public RetailItem(String itemName, String itemDescription, int itemQuantity,
                      double itemSalePrice, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice);
        this.itemSalePrice = itemSalePrice;
        this.totalUnitsSold = 0;
    }

    public double getItemSalePrice() {
        return itemSalePrice;
    }

    public int getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public void setTotalUnitsSold(int totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    @Override
    public String toString() {
        return "[R] " + super.toString() + System.lineSeparator()
                + String.format("\tsale price: $%.2f", itemSalePrice);
    }
}
