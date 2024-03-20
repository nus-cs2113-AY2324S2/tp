package command;

import minigame.TypingGame;
import player.PlayerProfile;

public class WorkCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();
    }

    public boolean isExit() {
        return false;
    }
}
