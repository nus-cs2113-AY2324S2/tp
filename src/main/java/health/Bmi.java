package health;

import utility.Constant;
import utility.CustomExceptions;
import utility.Parser;

import java.time.LocalDate;

/**
 * The Bmi class extends the Health class and provides functionality to calculate
 * and categorise the Body Mass Index based on user's height and weight.
 */
public class Bmi extends Health {
    /**
     * The height of the user in meters.
     */
    protected double height;

    /**
     * The weight of the user in kilograms.
     */
    protected double weight;

    /**
     * The Bmi of the user.
     */
    protected double bmiValue;

    /**
     * The Bmi's corresponding category.
     */
    protected String bmiCategory;

    protected LocalDate date;

    /**
     * Constructor for Bmi object.
     *
     * @param height A string representing the user's height.
     * @param weight A string representing the user's weight.
     * @throws AssertionError If height or weight values are not positive.
     */
    public Bmi(String height, String weight, String date) {

        this.height = Double.valueOf(height);
        this.weight = Double.valueOf(weight);

        assert this.height > 0 && this.weight > 0 : "Height and weight must be positive";

        this.date = Parser.parseDate(date);

        this.bmiValue = calculateBmiValue();
        this.bmiCategory = getBmiCategory(bmiValue);
    }

    /**
     * Split user input into Bmi command, height, weight and date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Bmi parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] getBmi(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[Constant.BMI_PARAMETERS];

        if (!input.contains(Constant.HEALTH_FLAG)
                || !input.contains(Constant.HEIGHT_FLAG)
                || !input.contains(Constant.WEIGHT_FLAG)
                || !input.contains(Constant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
        }

        int indexH = input.indexOf(Constant.HEALTH_FLAG);
        int indexHeight = input.indexOf(Constant.HEIGHT_FLAG);
        int indexWeight = input.indexOf(Constant.WEIGHT_FLAG);
        int indexDate = input.indexOf(Constant.DATE_FLAG);

        String command = input.substring(indexH + Constant.BMI_H_OFFSET, indexHeight).trim();
        String heightSubstring = input.substring(indexHeight + Constant.BMI_HEIGHT_OFFSET, indexWeight).trim();
        String weightSubstring = input.substring(indexWeight + Constant.BMI_WEIGHT_OFFSET, indexDate).trim();
        String dateSubstring = input.substring(indexDate + Constant.DATE_OFFSET).trim();

        if (command.isEmpty() || heightSubstring.isEmpty() || weightSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(Constant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        results[0] = command;
        results[1] = heightSubstring;
        results[2] = weightSubstring;
        results[3] = dateSubstring;

        return results;
    }

    /**
     * Calculates Bmi based on height and weight.
     *
     * @return The calculated Bmi value.
     * @throws AssertionError If calculated value is not positive.
     */
    public double calculateBmiValue() {
        double bmi = Math.round((weight / (Math.pow(height, Constant.POWER_OF_TWO))) * Constant.ROUNDING_FACTOR)
                / Constant.ROUNDING_FACTOR;
        assert bmi > 0: "BMI value must be positive";
        return bmi;
    }

    /**
     * Prints the Bmi category based on the calculated Bmi value.
     *
     * @param bmiValue The Bmi value to categorize.
     * @return A string presenting the Bmi category.
     * @throws AssertionError If calculated value is not positive.
     */
    public static String getBmiCategory(double bmiValue) {
        assert bmiValue > 0: "BMI value must be positive";

        if (bmiValue < Constant.UNDERWEIGHT_BMI_THRESHOLD) {
            return Constant.UNDERWEIGHT_MESSAGE;
        } else if (bmiValue < Constant.NORMAL_BMI_THRESHOLD) {
            return Constant.NORMAL_WEIGHT_MESSAGE;
        } else if (bmiValue < Constant.OVERWEIGHT_BMI_THRESHOLD) {
            return Constant.OVERWEIGHT_MESSAGE;
        } else if (bmiValue < Constant.OBESE_BMI_THRESHOLD) {
            return Constant.OBESE_MESSAGE;
        } else {
            return Constant.SEVERELY_OBESE_MESSAGE;
        }
    }

    /**
     * Returns a string containing calculated Bmi value and its corresponding category.
     *
     * @return A string of Bmi object.
     */
    @Override
    public String toString() {
        return this.date
                + System.lineSeparator()
                + Constant.BMI_MESSAGE_PREFIX
                + this.calculateBmiValue()
                + System.lineSeparator()
                + this.getBmiCategory(bmiValue);
    }
}
