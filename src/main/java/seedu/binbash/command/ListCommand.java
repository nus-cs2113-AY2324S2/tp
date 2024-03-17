package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ListCommand extends Command {

    public ListCommand(ItemList itemList) {
        super(itemList);
        commandLogger.fine("Creating List Command...");
    }

    public String execute() {
        return itemList.printList(itemList.getItemList());
    }
}
