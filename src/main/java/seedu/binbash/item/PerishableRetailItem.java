package seedu.binbash.item;

import java.time.LocalDate;
import java.util.Optional;

public class PerishableRetailItem extends RetailItem {
    public PerishableRetailItem(String itemName, String itemDescription, int itemQuantity,
                                Optional<LocalDate> itemExpirationDate, double itemSalePrice, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice);
    }

    @Override
    public String toString() {
        return "[P]" + super.toString();
    }
}
