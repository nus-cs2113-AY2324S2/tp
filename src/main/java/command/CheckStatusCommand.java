package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class CheckStatusCommand implements Command {
    public void execute(PlayerProfile profile) {
        ResponseManager.indentPrint(
                "Current Status:\n" + "Asset: " + profile.toString() + "\n");
    }

    public boolean isExit() {
        return false;
    }
}
