package seedu.binbash.command;

import java.util.logging.Logger;
import seedu.binbash.ItemList;

public abstract class Command {
    protected ItemList itemList;
    protected Logger commandLogger;
    protected String executionUiOutput;
    protected boolean hasToSave = false;

    protected Command() {
        commandLogger = Logger.getLogger("CommandLogger");
    }

    public String getExecutionUiOutput() {
        return executionUiOutput;
    }

    public boolean hasToSave() {
        return hasToSave;
    }

    public abstract boolean execute(ItemList itemList);
}
