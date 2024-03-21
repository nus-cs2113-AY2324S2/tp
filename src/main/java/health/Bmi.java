package health;

import utility.UiConstant;
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
     * The BMI category.
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

        this.height = Double.parseDouble(height);
        this.weight = Double.parseDouble(weight);

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
        String[] results = new String[UiConstant.BMI_PARAMETERS];

        if (!input.contains(UiConstant.HEALTH_FLAG)
                || !input.contains(UiConstant.HEIGHT_FLAG)
                || !input.contains(UiConstant.WEIGHT_FLAG)
                || !input.contains(UiConstant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(UiConstant.MISSING_PARAMETERS);
        }

        int indexH = input.indexOf(UiConstant.HEALTH_FLAG);
        int indexHeight = input.indexOf(UiConstant.HEIGHT_FLAG);
        int indexWeight = input.indexOf(UiConstant.WEIGHT_FLAG);
        int indexDate = input.indexOf(UiConstant.DATE_FLAG);

        String command = input.substring(indexH + UiConstant.BMI_H_OFFSET, indexHeight).trim();
        String heightSubstring = input.substring(indexHeight + UiConstant.BMI_HEIGHT_OFFSET, indexWeight).trim();
        String weightSubstring = input.substring(indexWeight + UiConstant.BMI_WEIGHT_OFFSET, indexDate).trim();
        String dateSubstring = input.substring(indexDate + UiConstant.DATE_OFFSET).trim();

        if (command.isEmpty() || heightSubstring.isEmpty() || weightSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(UiConstant.INSUFFICIENT_PARAMETERS_FOR_BMI);
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
        double bmi = Math.round((weight / (Math.pow(height, UiConstant.POWER_OF_TWO))) * UiConstant.ROUNDING_FACTOR)
                / UiConstant.ROUNDING_FACTOR;
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

        if (bmiValue < UiConstant.UNDERWEIGHT_BMI_THRESHOLD) {
            return UiConstant.UNDERWEIGHT_MESSAGE;
        } else if (bmiValue < UiConstant.NORMAL_BMI_THRESHOLD) {
            return UiConstant.NORMAL_WEIGHT_MESSAGE;
        } else if (bmiValue < UiConstant.OVERWEIGHT_BMI_THRESHOLD) {
            return UiConstant.OVERWEIGHT_MESSAGE;
        } else if (bmiValue < UiConstant.OBESE_BMI_THRESHOLD) {
            return UiConstant.OBESE_MESSAGE;
        } else {
            return UiConstant.SEVERELY_OBESE_MESSAGE;
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
                + UiConstant.BMI_MESSAGE_PREFIX
                + this.calculateBmiValue()
                + System.lineSeparator()
                + getBmiCategory(bmiValue);
    }
}
