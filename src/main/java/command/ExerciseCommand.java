package command;

import exception.InvalidMarkException;
import minigame.TicTacToe;
import player.PlayerProfile;
import ui.ResponseManager;

import java.util.Scanner;

public class ExerciseCommand implements Command {

    public void execute(PlayerProfile playerProfile) {

        ResponseManager.indentPrint("Please choose your mark: X or O\n");
        char playerMark = '-';
        while ((playerMark != 'X') && (playerMark != 'O')) {
            try {
                playerMark = getMark();
            } catch (InvalidMarkException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        TicTacToe game = new TicTacToe(playerMark);
        game.startGame();
        if (game.getStatus() == 1) {
            playerProfile.addHealth(1);
        } else if (game.getStatus() == -1) {
            playerProfile.loseHealth();
        }
    }

    public char getMark() throws InvalidMarkException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.equals("X") && !input.equals("O")) {
            throw new InvalidMarkException("Mark can only be X or O\n");
        }
        char playerMark = input.charAt(0);
        return playerMark;
    }

    public boolean isExit() {
        return false;
    }
}
