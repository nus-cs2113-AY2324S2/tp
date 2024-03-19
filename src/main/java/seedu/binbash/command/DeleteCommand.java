package seedu.binbash.command;

import java.util.regex.Pattern;
import java.util.logging.Level;
import seedu.binbash.ItemList;

public class DeleteCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("delete\\s(?<identifier>.+)");
    private String keyword;
    private int index;
    private boolean isIndex;

    public DeleteCommand(ItemList itemList, int index) {
        super(itemList);
        this.index = index;
        isIndex = true;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    public DeleteCommand(ItemList itemList, String keyword) {
        super(itemList);
        this.keyword = keyword;
        isIndex = false;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    public String execute() {
        if (isIndex) {
            assert index > 0 && index <= itemList.getItemCount(); // Ensure index out of bounds error is caught by Parser.
            commandLogger.log(Level.INFO, "Item was deleted using index");
            return itemList.deleteItem(index);
        }

        commandLogger.log(Level.INFO, "Item was deleted using item name");
        return itemList.deleteItem(keyword);
    }
}
