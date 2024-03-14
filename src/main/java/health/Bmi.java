package health;

/*
 * The BMI class extends the Health class and provides functionality
 * to calculate and categorise the Body Mass Index based on user's
 * height and weight.
 */
public class Bmi extends Health {
    public Bmi() {
        super();
    }

    /*
     * Calculates BMI based on height and weight, prints calculated BMI value,
     * and calls the printBMICategory method.
     */
    public static void calculateBMI() {
        double bmi = Health.weight / (Math.pow(Health.height, 2.0));
        System.out.printf("Your BMI is %.2f", bmi);
        Health.printNewLine();
        printBMICategory(bmi);
    }

    /*
     * Prints the BMI category based on the calculated BMI value.
     *
     * @param BMI The BMI value to categorize.
     */
    public static void printBMICategory(double bmi) {
        if (bmi < 18.5) {
            System.out.println("You're underweight.");
        } else if (bmi < 24.9) {
            System.out.println("Great! You're within normal range.");
        } else if (bmi < 29.9) {
            System.out.println("You're overweight.");
        } else if (bmi < 39.9) {
            System.out.println("You're obese.");
        } else {
            System.out.println("You're severely obese.");
        }
    }
}
