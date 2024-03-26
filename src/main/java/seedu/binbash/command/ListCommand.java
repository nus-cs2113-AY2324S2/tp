package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.ui.Ui;
import seedu.binbash.storage.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        commandLogger.fine("Creating List Command...");
    }

    public boolean execute(Ui ui, ItemList itemList, Storage storage) {
        executionUiOutput = itemList.printList(itemList.getItemList());
        return true;
    }
}
