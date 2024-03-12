package health;

/*
 * The Parser class provides utility methods for parsing user input
 * related to the Health class.
 */
public class Parser {
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
}
