package command;

import minigame.TicTacToe;
import player.PlayerProfile;
import ui.ResponseManager;

import java.util.Scanner;

public class ExerciseCommand implements Command {

    public void execute(PlayerProfile playerProfile) {
        Scanner scanner = new Scanner(System.in);
        ResponseManager.indentPrint("Please choose your mark: X or O");
        String input = scanner.nextLine();
        char playerMark = input.charAt(0);
        TicTacToe game = new TicTacToe(playerMark);
        game.startGame();
        game.outputResult();
    }

    public boolean isExit() {
        return false;
    }
}
