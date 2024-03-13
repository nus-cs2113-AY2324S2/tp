package ai;

import java.util.Random;

/**
 * Ai of the game.
 */
public class Ai {
    public Ai() {
    }

    /**
     * Returns a random number representing the direction chosen by the AI.
     */
    public int getAiDirection() {
        Random rand = new Random();
        return rand.nextInt(3);
    }
}
