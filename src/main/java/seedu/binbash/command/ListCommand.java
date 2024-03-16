package seedu.binbash.command;

import seedu.binbash.ItemList;
import java.util.regex.Pattern;

public class ListCommand extends Command {

    public static final String COMMAND_STRING = "list";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("^list");


    public ListCommand(ItemList itemList) {
        super(itemList);
    }

    public String execute() {
        return itemList.printList(itemList.getItemList());
    }
}
