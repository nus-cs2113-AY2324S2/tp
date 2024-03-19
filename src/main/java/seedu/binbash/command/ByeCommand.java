package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ByeCommand extends Command {
    public ByeCommand(ItemList itemList) {
        super(itemList);
        commandLogger.fine("Creating Bye Command...");
    }

    @Override
    public boolean execute() {
        executionUiOutput = "";
        return true;
    }
}
