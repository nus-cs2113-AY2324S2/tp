package health;

import utility.Constant;
import utility.CustomExceptions;

/*
 * The Bmi class extends the Health class and provides functionality
 * to calculate and categorise the Body Mass Index based on user's
 * height and weight.
 */
public class Bmi extends Health {
    /*
     * The height of the user in meters.
     */
    protected double height;

    /*
     * The weight of the user in kilograms.
     */
    protected double weight;
    protected double bmiValue;
    protected String bmiCategory;

    /*
     * Constructor for Health object.
     */
    public Bmi(String height, String weight) {
        this.height = Double.valueOf(height);
        this.weight = Double.valueOf(weight);
        this.bmiValue = calculateBmiValue();
        this.bmiCategory = getBmiCategory(bmiValue);
    }

    public static String[] getBmi(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[Constant.BMI_PARAMETERS];

        if (!input.contains("/h") || !input.contains("/height:") || !input.contains("/weight:")) {
            throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
        }

        int indexH = input.indexOf("/h");
        int indexHeight = input.indexOf("/height");
        int indexWeight = input.indexOf("/weight");

        String command = input.substring(indexH + Constant.BMI_H_OFFSET, indexHeight).trim();
        String heightSubstring = input.substring(indexHeight + Constant.BMI_HEIGHT_OFFSET, indexWeight).trim();
        String weightSubstring = input.substring(indexWeight + Constant.BMI_WEIGHT_OFFSET).trim();

        if (command.isEmpty() || heightSubstring.isEmpty() || weightSubstring.isEmpty()) {
            // throw new CustomExceptions(Constant.UNSPECIFIED_PARAMETERS;
        }

        results[0] = command;
        results[1] = heightSubstring;
        results[2] = weightSubstring;

        return results;
    }

    /*
     * Calculates BMI based on height and weight, prints calculated BMI value,
     * and calls the printBMICategory method.
     */
    public double calculateBmiValue() {
        return Math.round((weight / (Math.pow(height, 2.0))) * 100.0) / 100.0;
    }

    /*
     * Prints the BMI category based on the calculated Bmi value.
     *
     * @param Bmi The BMI value to categorize.
     */
    public static String getBmiCategory(double bmiValue) {
        if (bmiValue < 18.5) {
            return Constant.UNDERWEIGHT_MESSAGE;
        } else if (bmiValue < 24.9) {
            return Constant.NORMAL_WEIGHT_MESSAGE;
        } else if (bmiValue < 29.9) {
            return Constant.OVERWEIGHT_MESSAGE;
        } else if (bmiValue < 39.9) {
            return Constant.OBESE_MESSAGE;
        } else {
            return Constant.SEVERELY_OBESE_MESSAGE;
        }
    }

    @Override
    public String toString() {
        return "Your BMI is " + this.calculateBmiValue() + System.lineSeparator() + this.getBmiCategory(bmiValue);
    }
}
