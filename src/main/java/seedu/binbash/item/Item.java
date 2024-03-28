package seedu.binbash.item;

public abstract class Item {
    protected final String itemName;
    protected final String itemDescription;
    protected int itemQuantity;
    protected final double itemCostPrice;
    protected int totalUnitsPurchased;

    public Item(String itemName, String itemDescription, int itemQuantity, double itemCostPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemCostPrice = itemCostPrice;
        this.totalUnitsPurchased = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemCostPrice() {
        return itemCostPrice;
    }

    public int getTotalUnitsPurchased() {
        return totalUnitsPurchased;
    }

    public void setTotalUnitsPurchased(int totalUnitsPurchased) {
        this.totalUnitsPurchased = totalUnitsPurchased;
    }

    @Override
    public String toString() {
        return String.format("%s" + System.lineSeparator() +
                        "\tdescription: %s" + System.lineSeparator() +
                        "\tquantity: %d" + System.lineSeparator() +
                        "\tcost price: $%.2f",
                itemName,
                itemDescription,
                itemQuantity,
                itemCostPrice
        );
    }
}
