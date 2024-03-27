package seedu.binbash.command;

import java.util.regex.Pattern;
import java.util.logging.Level;
import seedu.binbash.ItemList;

public class DeleteCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("delete\\s(?<identifier>.+)");
    private String keyword;
    private int index;
    private boolean isIndex;

    public DeleteCommand(int index) {
        this.index = index;
        isIndex = true;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    public DeleteCommand(String keyword) {
        this.keyword = keyword;
        isIndex = false;
        commandLogger.fine(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    public boolean execute(ItemList itemList) {
        if (isIndex) {
            if (index <= 0 || index > itemList.getItemCount()) {
                executionUiOutput = "Index is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemList.getItemCount();
            commandLogger.log(Level.INFO, "Delete identifier is detected as an index");
            executionUiOutput = itemList.deleteItem(index);
        } else {
            commandLogger.log(Level.INFO, "Delete identifier is detected as an item name");
            executionUiOutput = itemList.deleteItem(keyword);
        }
        hasToSave = true;
        return true;
    }
}
