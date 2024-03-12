package health;

/**
 * The Health class represents health information of the user.
 */
public class Health {
    /**
     * The height of the user in meters.
     */
    protected static double height;

    /**
     * The weight of the user in kilograms.
     */
    protected static double weight;

    /**
     * Constructor for Health object.
     */
    public Health() {
        this.height = height;
        this.weight = weight;
    }

    /**
     * Prints a new line.
     */
    public static void printNewLine() {
        System.out.println();
    }
}
