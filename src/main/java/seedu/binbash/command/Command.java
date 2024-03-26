package seedu.binbash.command;

import java.util.logging.Logger;
import seedu.binbash.ItemList;
import seedu.binbash.ui.Ui;
import seedu.binbash.storage.Storage;

public abstract class Command {
    protected ItemList itemList;
    protected Logger commandLogger;
    protected String executionUiOutput;

    protected Command() {
        commandLogger = Logger.getLogger("CommandLogger");
    }

    public String getExecutionUiOutput() {
        return executionUiOutput;
    }

    public abstract boolean execute(Ui ui, ItemList itemList, Storage storage);
}
