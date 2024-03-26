package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        commandLogger.fine("Creating List Command...");
    }

    public boolean execute(Ui ui, ItemList itemList) {
        executionUiOutput = itemList.printList(itemList.getItemList());
        return true;
    }
}
