package seedu.binbash.item;

import java.time.LocalDate;
import java.util.Optional;

public class OperationalItem extends Item {
    public OperationalItem(String itemName, String itemDescription, int itemQuantity,
                           Optional<LocalDate> itemExpirationDate, double itemSalePrice, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice);
    }

    @Override
    public String toString() {
        return "[O] " + super.toString();
    }
}
