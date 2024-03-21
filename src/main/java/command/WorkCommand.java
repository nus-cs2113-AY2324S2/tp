package command;

import minigame.TypingGame;
import player.PlayerProfile;
import ui.ResponseManager;

public class WorkCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();
        playerProfile.loseHealth();
        if (game.getAccuracy() >= 50) {
            assert game.getAccuracy() <= 100 : "Accuracy should not exceed 100";
            int earned = (game.getAccuracy() * 1000 / 100);
            assert earned >= 0 : "Earned should not be negative";
            playerProfile.addAsset(earned);
            ResponseManager.indentPrint("You have earned $" + earned + "\n");
        }
    }

    public boolean isExit() {
        return false;
    }
}
