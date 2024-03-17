package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class AddCommand extends Command {

    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("add\\s+n/(?<itemName>.+?)\\s+d/(?<itemDescription>.+)\\s+q/(?<itemQuantity>.+)\\s"
                    + "+e/(?<itemExpirationDate>.+)+s/(?<itemSalePrice>.+)+c/(?<itemCostPrice>.+)");
    private final String itemName;
    private final String itemDescription;
    private final int itemQuantity;
    private final String itemExpirationDate;
    private final double itemSalePrice;
    private final double itemCostPrice;

    public AddCommand(ItemList itemList, String itemName, String itemDescription, int itemQuantity,
                      String itemExpirationDate, double itemSalePrice, double itemCostPrice) {
        super(itemList);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;

        commandLogger.fine(String.format(
                "Creating Add Command... itemName: %s, itemDescription: %s, itemQuantity: %d, itemExpirationDate: %s"
                        + "itemSalePrice: %f, itemCostPrice: %f",
                itemName,
                itemDescription,
                itemQuantity,
                itemExpirationDate,
                itemSalePrice,
                itemCostPrice
        ));
    }

    @Override
    public String execute() {
        return itemList.addItem(itemName, itemDescription, itemQuantity, itemExpirationDate,
                itemSalePrice, itemCostPrice);
    }
}
