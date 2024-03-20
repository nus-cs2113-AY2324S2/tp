package command;

import ui.ResponseManager;

public class ExitCommand implements Command {
    public void execute() {
        ResponseManager.printGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
