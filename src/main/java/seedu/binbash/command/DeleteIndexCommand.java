package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public class DeleteIndexCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("delete\\s(?<index>\\d+)");
    private final int index;

    public DeleteIndexCommand(ItemList itemList, int index) {
        super(itemList);
        this.index = index;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    public String execute() {
        assert index > 0 && index <= itemList.getItemCount(); // Ensure index out of bounds error is caught by Parser.

        return itemList.deleteItem(index);
    }
}
