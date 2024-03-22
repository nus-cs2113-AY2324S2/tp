package seedu.binbash.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class Item {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected final String itemName;
    protected final String itemDescription;
    protected int itemQuantity;
    protected final Optional<LocalDate> itemExpirationDate;
    protected final double itemSalePrice;
    protected final double itemCostPrice;



    public Item(String itemName, String itemDescription, int itemQuantity, Optional<LocalDate> itemExpirationDate,
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
        return itemExpirationDate.map(x -> x.format(DATE_TIME_FORMATTER)).orElse("N.A.");
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
                getItemExpirationDate(),
                itemSalePrice,
                itemCostPrice
        );
    }
}
