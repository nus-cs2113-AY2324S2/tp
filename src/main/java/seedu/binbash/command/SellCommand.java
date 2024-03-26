package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.ui.Ui;

import java.util.regex.Pattern;

public class SellCommand extends Command{
    public static final String COMMAND = "sell";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "sell\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<sellQuantity>.+)"
    );
    private final String itemName;
    private final int sellQuantity;

    public SellCommand(String itemName, int sellQuantity) {
        this.itemName = itemName;
        this.sellQuantity = sellQuantity;

        commandLogger.fine(String.format(
                "Creating Sell Command... itemName: %s, sellQuantity: %d",
                itemName,
                sellQuantity
        ));
    }

    @Override
    public boolean execute(Ui ui, ItemList itemList) {
        executionUiOutput = itemList.updateItemQuantity(itemName, sellQuantity, COMMAND);
        hasToSave = true;
        return true;
    }
}
