package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class AddCommand extends Command {
    public static final String COMMAND_STRING = "add";

    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("add\\s+n/(?<itemName>.+?)\\s+d/(?<itemDescription>.+)\\s+q/(?<itemQuantity>.+)\\s"
                    + "+e/(?<itemExpirationDate>.+)+s/(?<itemSalePrice>.+)+c/(?<itemCostPrice>.+)");



    private String itemName;
    private String itemDescription;
    private int itemQuantity;
    private String itemExpirationDate;
    private  double itemSalePrice;
    private double itemCostPrice;

    public AddCommand(ItemList itemList, String itemName, String itemDescription, int itemQuantity,
                      String itemExpirationDate, double itemSalePrice, double itemCostPrice) {
        super(itemList);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
    }

    @Override
    public String execute() {
        return itemList.addItem(itemName, itemDescription, itemQuantity, itemExpirationDate,
                itemSalePrice, itemCostPrice);
    }
}
