package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class AddCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "add\\s+n/(?<itemName>.+?)\\s+d/(?<itemDescription>.+)"
    );

    private final String itemName;
    private final String itemDescription;

    public AddCommand(ItemList itemList, String itemName, String itemDescription) {
        super(itemList);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    @Override
    public String execute() {
        return itemList.addItem(itemName, itemDescription);
    }
}
