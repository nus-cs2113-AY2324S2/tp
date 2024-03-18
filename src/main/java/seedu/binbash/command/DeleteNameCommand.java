package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class DeleteNameCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("deletename\\s(?<itemName>\\d+)");
    private final String itemName;

    public DeleteNameCommand(ItemList itemList, String itemName) {
        super(itemList);
        this.itemName = itemName;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemName: %s",
                itemName
        ));
    }

    public String execute() {
        assert itemName != null; // Ensure itemName has a value and not null.

        return itemList.deleteItemName(itemName);
    }
}
