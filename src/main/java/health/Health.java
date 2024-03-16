package health;

/*
 * The Health class represents health information of the user.
 */
public class Health {
    /*
     * The height of the user in meters.
     */
    protected static double height;

    /*
     * The weight of the user in kilograms.
     */
    protected static double weight;

    /*
     * Constructor for Health object.
     */
    public Health() {
        this.height = height;
        this.weight = weight;
    }

    /*
     * Sets the height and weight property of the Health class according to
     * user input.
     *
     * @param userInput The user input string in the format "height x.yz" or "weight x.yz".
     */
    public static void setHeightAndWeight(String userInput) {
        String[] parts = userInput.split(" ");

        if (parts.length != 2) {
            System.out.println("Invalid input format. Please enter in the correct format.");
            return;
        }

        try {
            if (parts[0].equals("height")) {
                Health.height = Double.parseDouble(parts[1]);
                System.out.printf("Your height is %.2fm.", Health.height);
            } else {
                Health.weight = Double.parseDouble(parts[1]);
                System.out.printf("Your weight is %.2fkg.", Health.weight);
            }
            Health.printNewLine();
        } catch (NumberFormatException e) {
            System.out.println("Invalid value entered. Please try again.");
        }
    }

    /*
     * Prints a new line.
     */
    public static void printNewLine() {
        System.out.println();
    }
}
