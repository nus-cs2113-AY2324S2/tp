package seedu.duke.ai;

import java.util.Random;

/**
 * Ai of the game.
 */
public class Ai {
    private static int minDirection = 0;
    private static int maxDirection = 2;

    public Ai() {
    }

    /**
     * Returns a random number representing the direction chosen by the AI.
     */
    public static int getAiDirection() {
        Random rand = new Random();
        int direction = rand.nextInt(3);
        assert direction >= Ai.getMinDirection() && direction <= Ai.getMaxDirection() :
                "Illegal AI direction generated!";
        return direction;
    }

    public static int getMinDirection() {
        return minDirection;
    }

    public static int getMaxDirection() {
        return maxDirection;
    }
}
