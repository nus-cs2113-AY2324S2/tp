package seedu.binbash.command;

import seedu.binbash.ItemList;

public class ByeCommand extends Command {
    public ByeCommand() {
        commandLogger.fine("Creating Bye Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = "Bye!";
        return true;
    }
}
