package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ListCommand extends Command {

    public ListCommand(ItemList itemList) {
        super(itemList);
        commandLogger.fine("Creating List Command...");
    }

    public boolean execute() {
        executionUiOutput = itemList.printList(itemList.getItemList());
        return true;
    }
}
