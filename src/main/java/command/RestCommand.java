package command;

import minigame.MCQGame;

public class RestCommand implements Command {
    public void execute() {
        MCQGame game = new MCQGame();
        game.startGame();
        game.outputResult();
    }

    public boolean isExit() {
        return false;
    }
}
