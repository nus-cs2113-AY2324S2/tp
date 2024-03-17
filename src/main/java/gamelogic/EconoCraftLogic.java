package gamelogic;

import exception.JobSelectException;
import exception.NameInputException;
import player.PlayerProfile;
import ui.Parser;
import ui.ResponseManager;

import java.util.Scanner;

public class EconoCraftLogic {
    private final PlayerProfile playerProfile;

    private EconoCraftLogic(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public static EconoCraftLogic initializeGame() {
        String playerName = "";
        String jobType = "";

        Scanner input = new Scanner(System.in);
        ResponseManager.printGameInit();
        while (playerName.isEmpty()) {
            try {
                playerName = Parser.parseName(input.nextLine());
            } catch (NameInputException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }

        ResponseManager.printJobSelect();
        while (jobType.isEmpty()) {
            try {
                jobType = Parser.parseCareer(input.nextLine());
            } catch (JobSelectException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        input.close();

        PlayerProfile playerProfile = new PlayerProfile(playerName, jobType);
        ResponseManager.printWelcome(playerProfile);

<<<<<<< HEAD
        seedu.duke.tictactoe.TicTacToe game = new seedu.duke.tictactoe.TicTacToe('X');
        game.gameStart();
=======

        seedu.duke.tictactoe.TicTacToe game = new seedu.duke.tictactoe.TicTacToe('X');
        game.gameStart();

     
>>>>>>> 9d19ac2734dcdf993a693e56d086a8ba88a054e4
        return new EconoCraftLogic(playerProfile);
    }

    public void startGame() {
        System.out.println("Game started");
    }
}
