package seedu.binbash.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PerishableOperationalItem extends OperationalItem {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDate itemExpirationDate;

    public PerishableOperationalItem(String itemName, String itemDescription, int itemQuantity,
                                     LocalDate itemExpirationDate, double itemCostPrice) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice);
        this.itemExpirationDate = itemExpirationDate;
    }

    public String getItemExpirationDate() {
        return itemExpirationDate.format(DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[P] " + super.toString() + System.lineSeparator() +
                String.format("\texpiry date: %s", itemExpirationDate.format(DATE_TIME_FORMATTER));
    }
}
