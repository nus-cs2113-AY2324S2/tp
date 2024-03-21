package seedu.duke;

import seedu.duke.ai.Ai;

public enum CommandList {
    BYE, SHOOT
    ;

    /**
     * Exits the program
     */
    public static boolean goalCheck(int userInput, int AiInput) {
        return userInput != AiInput;
    }
    public static void executeShoot(Parser userCommandReader){
        String selectedDirection = userCommandReader.getArgumentTokens()[0];
        int selectedDirectionIndex =  Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
    }
}

