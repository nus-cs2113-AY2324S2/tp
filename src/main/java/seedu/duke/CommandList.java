package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

public enum CommandList {
    BYE, SHOOT, PENALTY
//    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    //insert new user command name here
    ;

    /**
     * Exits the program
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        Ui.setIsRunning(false);
    }

    public static boolean goalCheck(int userInput, int AiInput) {
        assert userInput >= 0 && userInput <= 2 :
                "Illegal userInput generated!";
        assert AiInput >= 0 && AiInput <= 2 :
                "Illegal AiInput generated!";
        return userInput != AiInput;
    }

    public static void executeShoot(Parser userCommandReader) {
        String selectedDirection = userCommandReader.getArgumentTokens()[0];
        int selectedDirectionIndex = Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
    }

    public static void executePenalty() {
        Penalty.executePenalty();
    }
    
    //insert new command here
}

