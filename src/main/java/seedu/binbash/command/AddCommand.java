package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class AddCommand extends Command {

//    public static final Pattern COMMAND_FORMAT = Pattern.compile(
//            "add\\s" + "n/(?<itemName>.+?)\\s" + "d/(?<itemDescription>.+?)\\s" + "(q/(?<itemQuantity>.+?))?"
//                    + "(e/(?<itemExpirationDate>.+?))?" + "s/(?<itemSalePrice>.+?)\\s" + "c/(?<itemCostPrice>.+)"
//    );

    /**
     * TODO: I understand the formatting of this is cancer. The trailing comments are exceeding the wraparound
     * TODO: limit. We have to fix it in the future, but I hope they help clarify the regex for future rectifications.
     *
     * As of now, only itemQuantity(q/) and and itemExpirationDate(e/) are optional groups.
     */
    public static final Pattern COMMAND_FORMAT = Pattern.compile(

            "add\\s+" +                                             // Match the 'add' command followed by one or more whitespace characters.

                    "n/(?<itemName>.+?)(?=d/)" +                    // Match 'n/' followed by any characters for `itemName`, lazy match, until seeing 'd/'.

                    "d/(?<itemDescription>.+?)(?=(q/|e/|s/))" +     // Match 'd/' followed by any characters for `itemDescription`, lazy match, until seeing 'q/', 'e/', or 's/'.

                    "(q/(?<itemQuantity>.+?)(?=(e/|s/)))?\\s*" +    // Optionally match 'q/' followed by the item quantity.

                    "(e/(?<itemExpirationDate>.+?)(?=s/))?\\s*" +   // Optionally match 'e/' followed by the expiration date.

                    "(s/(?<itemSalePrice>.+?))(?=c/)" +             // Match 's/' followed by the sale price, until seeing 'c/'.

                    "c/(?<itemCostPrice>.+)"                        // Finally, match 'c/' followed by the cost price.
    );


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
        
        assert itemName != null && !itemName.trim().isEmpty();
        assert itemQuantity >= 0;

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
    public boolean execute() {
        executionUiOutput = itemList.addItem(itemName, itemDescription, itemQuantity, itemExpirationDate,
                itemSalePrice, itemCostPrice);
        return true;
    }
}
