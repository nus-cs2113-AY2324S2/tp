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
        ResponseManager.printGameInit();
        String playerName = getName();

        ResponseManager.printJobSelect();
        String jobType = getJob();

        PlayerProfile playerProfile = new PlayerProfile(playerName, jobType);
        ResponseManager.printWelcome(playerProfile);
     
        return new EconoCraftLogic(playerProfile);
    }

    private static String getJob() {
        Scanner input = new Scanner(System.in);
        String jobType = "";
        while (jobType.isEmpty()) {
            try {
                jobType = Parser.parseCareer(input.nextLine());
            } catch (JobSelectException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        input.close();
        return jobType;
    }

    private static String getName() {
        Scanner input = new Scanner(System.in);
        String playerName = "";
        while (playerName.isEmpty()) {
            try {
                playerName = Parser.parseName(input.nextLine());
            } catch (NameInputException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        input.close();
        return playerName;
    }

    public void startEcono() {
        System.out.println("Game started");
    }
}
