package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ListCommand extends Command {

    public ListCommand(ItemList itemList) {
        super(itemList);
    }

    public String execute() {
        return itemList.printList(itemList.getItemList());
    }
}
