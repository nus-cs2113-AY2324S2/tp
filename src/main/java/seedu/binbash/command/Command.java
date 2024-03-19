package seedu.binbash.command;

import java.util.logging.Logger;
import seedu.binbash.ItemList;

public abstract class Command {
    protected ItemList itemList;
    protected Logger commandLogger;
    protected String executionUiOutput;

    protected Command(ItemList itemList) {
        this.itemList = itemList;
        commandLogger = Logger.getLogger("CommandLogger");
    }

    public String getExecutionUiOutput() {
        return executionUiOutput;
    }

    public abstract boolean execute();
}
