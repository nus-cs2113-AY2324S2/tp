package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

public enum CommandList {
    BYE, SHOOT
    ;

    /**
     * Exits the program
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        Ui.setIsRunning(false);
    }

    public static boolean goalCheck(int userInput, int AiInput) {
        return userInput != AiInput;
    }

    public static void executeShoot(Parser userCommandReader) {
        String selectedDirection = userCommandReader.getArgumentTokens()[0];
        int selectedDirectionIndex = Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
    }

    //insert new command here
}

