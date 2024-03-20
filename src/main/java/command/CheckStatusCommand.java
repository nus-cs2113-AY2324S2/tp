package command;

import ui.ResponseManager;

public class CheckStatusCommand implements Command {
    public void execute() {
        ResponseManager.indentPrint(
                "Current Status:\n" + "Asset: " + profile.toString());
    }

    public boolean isExit() {
        return false;
    }
}
