package seedu.duke;

import seedu.duke.ai.Ai;
import seedu.duke.ui.Ui;

public class Save {

    public static void executeSave() {
        int userSaveDirection = getUserSaveDirection();
        int aiPenaltyDirection = Ai.getAiDirection();
        boolean isGoalSaved = userSaveDirection == aiPenaltyDirection;

        Formatter.printSaveResult(isGoalSaved);
        Ui.roundCount++;
    }

    private static int getUserSaveDirection() {
        int userDirection;
        do {
            System.out.print("Enter the direction to save the penalty (0, 1, or 2): ");
            String directionString = Ui.IN.nextLine().trim();
            userDirection = ShootDirectionConverter.convertToShootDirection(directionString);
            if (userDirection == -1) {
                System.out.println("Invalid direction! Please enter 0, 1, or 2.");
            }
        } while (userDirection == -1);
        return userDirection;
    }

}
