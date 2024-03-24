package seedu.duke;

import seedu.duke.ai.Ai;
import seedu.duke.ui.Ui;

/**
 * Handles the penalty command for the game.
 */
public class Penalty {
    private static final String PENALTY_PROMPT = "Enter the direction to shoot the penalty (0, 1, or 2): ";

    /**
     * Executes the penalty command.
     */
    public static void executePenalty() {
        int userDirection = getUserPenaltyDirection();
        int aiDirection = Ai.getAiDirection();

        boolean isGoal = CommandList.goalCheck(userDirection, aiDirection);
        Formatter.printGoalAfterShot(isGoal);
        Ui.roundCount++;
    }

    /**
     * Prompts the user to enter the penalty direction and returns the corresponding integer value.
     *
     * @return The user's penalty direction as an integer (0, 1, or 2).
     */
    private static int getUserPenaltyDirection() {
        int userDirection;
        do {
            System.out.print(PENALTY_PROMPT);
            String directionString = Ui.IN.nextLine().trim();
            userDirection = convertToShootDirection(directionString);
            if (userDirection == -1) {
                System.out.println("Invalid direction! Please enter 0, 1, or 2.");
            }
        } while (userDirection == -1);
        return userDirection;
    }

    /**
     * Converts the user's input direction to an integer value.
     *
     * @param direction The user's input direction as a string.
     * @return The user's input direction as an integer (0, 1, or 2), or -1 if the input is invalid.
     */
    public static int convertToShootDirection(String direction) {
        int directionIndex;
        try {
            directionIndex = Integer.parseInt(direction);
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }

        if (directionIndex >= Ai.getMinDirection() && directionIndex <= Ai.getMaxDirection()) {
            return directionIndex;
        } else {
            return -1; // Invalid direction
        }
    }
}
