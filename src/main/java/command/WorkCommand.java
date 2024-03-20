package command;

import minigame.TypingGame;

public class WorkCommand implements Command {
    public void execute() {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();

    }

    public boolean isExit() {
        return false;
    }
}
