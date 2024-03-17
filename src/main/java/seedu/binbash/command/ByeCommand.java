package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ByeCommand extends Command {
    public ByeCommand(ItemList itemList) {
        super(itemList);
    }

    @Override
    public String execute() {
        return "";
    }
}
