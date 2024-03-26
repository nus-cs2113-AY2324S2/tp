package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

import java.util.ArrayList;

public enum CommandList {
    BYE, SHOOT, PENALTY, UPGRADE
    //insert new user command name here
    ;

    /**
     * Exits the program
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        Ui.setIsRunning(false);
    }

    public static boolean goalCheck(int userInput, int aiInput) {
        assert userInput >= 0 && userInput <= 2 :
                "Illegal userInput generated!";
        assert aiInput >= 0 && aiInput <= 2 :
                "Illegal aiInput generated!";
        return userInput != aiInput;
    }

    public static void executeShoot(String[] readArgumentTokens) {
        PlayerList.L1.get(Ui.curplayer).printSelfInfo();
        PlayerList.L1.get(Ui.curplayer).printGoalBeforeShoot();
        String selectedDirection = readArgumentTokens[0];
        int selectedDirectionIndex = Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
    }

    public static void executePenalty() {
        Penalty.executePenalty();
    }
    public static void executeUpgrade(String[] level){
        String upgradeLevel = level[0];
        int upgradeLevelIndex = Integer.parseInt(upgradeLevel);

        PlayerList.L1.get(Ui.curplayer).upgradePower(upgradeLevelIndex);
        PlayerList.L1.get(Ui.curplayer).printSelfInfo();
    }
    //insert new command here
}

