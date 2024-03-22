package seedu.binbash;

public class Item {
    private final String itemName;
    private final String itemDescription;
    private final int itemQuantity;
    private final String itemExpirationDate;
    private final double itemSalePrice;
    private final double itemCostPrice;



    public Item(String itemName, String itemDescription, int itemQuantity, String itemExpirationDate,
                double itemSalePrice, double itemCostPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
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
    public String getItemExpirationDate() {
        return itemExpirationDate;
    }

    public double getItemSalePrice() {
        return itemSalePrice;
    }

    public double getItemCostPrice() {
        return itemCostPrice;
    }

    @Override
    public String toString() {
        return String.format("%s" + System.lineSeparator() +
                        "\tdescription: %s" + System.lineSeparator() +
                        "\tquantity: %d" + System.lineSeparator() +
                        "\texpiry date: %s" + System.lineSeparator() +
                        "\tsale price: $%.2f" + System.lineSeparator() +
                        "\tcost price: $%.2f",
                itemName,
                itemDescription,
                itemQuantity,
                itemExpirationDate,
                itemSalePrice,
                itemCostPrice
        );
    }
}
