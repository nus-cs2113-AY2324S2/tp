package command;

import minigame.MCQGame;
import player.PlayerProfile;

public class RestCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        MCQGame game = new MCQGame();
        game.startGame();
        game.outputResult();
        if(game.getCorrectCount() >= 1) {
            playerProfile.addHealth(1);
        }
    }

    public boolean isExit() {
        return false;
    }
}
