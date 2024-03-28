package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class ExitCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.printGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
