package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("delete\\s(?<index>\\d+)");
    protected int index;

    public DeleteCommand(ItemList itemList, int index) {
        super(itemList);

        this.index = index;
    }

    public String execute() {
        return itemList.deleteItem(index);
    }
}
