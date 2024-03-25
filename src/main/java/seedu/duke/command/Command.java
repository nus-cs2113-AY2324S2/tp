package seedu.duke.command;

import seedu.duke.FAP;
import seedu.duke.modules.ModuleList;

import java.util.logging.Logger;

public abstract class Command {
    protected ModuleList moduleList = FAP.moduleList;
    protected Logger logger = Logger.getLogger("Command");
    public void setData (ModuleList moduleList) {
        this.moduleList = moduleList;
    }
    public abstract void execute(String userInput);
}
