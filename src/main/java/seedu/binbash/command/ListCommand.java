package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ListCommand extends Command {

    public ListCommand() {
        commandLogger.fine("Creating List Command...");
    }

    public boolean execute(ItemList itemList) {
        executionUiOutput = itemList.printList(itemList.getItemList());
        return true;
    }
}
