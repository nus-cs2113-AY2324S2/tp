package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class HelpCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.printHelp();
    }

    public boolean isExit() {
        return false;
    }
}
