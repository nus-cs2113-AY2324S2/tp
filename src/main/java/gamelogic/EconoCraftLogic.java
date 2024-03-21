package gamelogic;

import command.Command;
import command.CommandFactory;
import exception.CommandInputException;
import exception.JobSelectException;
import exception.NameInputException;
import player.PlayerProfile;
import ui.Parser;
import ui.ResponseManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EconoCraftLogic {
    private static final Scanner userInput = new Scanner(System.in);
    private final PlayerProfile playerProfile;

    private EconoCraftLogic(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public static EconoCraftLogic initializeGame() {
        ResponseManager.printGameInit();
        String playerName = "";
        String jobType = "";
        try {
            playerName = getName();
        } catch (NoSuchElementException e) {
            ResponseManager.printGoodbye();
            System.exit(0);
        }

        ResponseManager.printJobSelect();
        try {
            jobType = getJob();
        } catch (NoSuchElementException e) {
            ResponseManager.printGoodbye();
            System.exit(0);
        }

        PlayerProfile playerProfile = new PlayerProfile(playerName, jobType);
        ResponseManager.printWelcome(playerProfile);
        return new EconoCraftLogic(playerProfile);

    }

    private static String getJob() {
        String jobType = "";
        while (jobType.isEmpty()) {
            try {
                jobType = Parser.parseCareer(userInput.nextLine());
            } catch (JobSelectException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        return jobType;
    }

    private static String getName() {
        String playerName = "";
        while (playerName.isEmpty()) {
            try {
                playerName = Parser.parseName(userInput.nextLine());
            } catch (NameInputException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        return playerName;
    }

    public void startEcono() {
        ResponseManager.printHelp();
        boolean exitFlag = false;

        while (!exitFlag) {
            try {
                Command command = CommandFactory.create(userInput.nextLine());
                command.execute(playerProfile);
                exitFlag = command.isExit();
            } catch (CommandInputException error) {
                ResponseManager.indentPrint(error.getMessage());
            }
        }
        userInput.close();
    }
}
