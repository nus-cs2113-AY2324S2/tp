package seedu.binbash.command;

import seedu.binbash.ItemList;

public abstract class Command {
    protected ItemList itemList;

    protected Command(ItemList itemList) {
        this.itemList = itemList;
    }

    public abstract String execute();
}
