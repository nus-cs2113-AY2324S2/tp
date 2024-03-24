package seedu.duke;

import seedu.duke.ai.Ai;

/**
 * Converts a given direction string to an integer representing the shoot direction.
 */
public class ShootDirectionConverter {
    /**
     * Returns the direction to shoot the football based on the given string input.
     *
     * @param direction The direction string, should be "0", "1", or "2".
     * @return The corresponding integer direction (0, 1, or 2) if the input is valid, -1 otherwise.
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