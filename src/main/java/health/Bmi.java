package health;

import ui.Handler;
import utility.Parser;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.UiConstant;
import utility.HealthConstant;

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

    //@@author j013n3
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

        assert this.height > 0 && this.weight > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;

        this.date = Parser.parseDate(date);

        this.bmiValue = calculateBmiValue();
        this.bmiCategory = getBmiCategory(bmiValue);
    }

    /**
     * Returns height recorded in Bmi object.
     *
     * @return The height recorded in the Bmi object.
     */
    public String getHeight() {
        return String.format("%.2f", height);
    }

    /**
     * Returns weight recorded in Bmi object.
     *
     * @return The weight recorded in the Bmi object.
     */
    public String getWeight() {
        return String.format("%.2f", weight);
    }

    /**
     * Returns date recorded in Bmi object.
     *
     * @return The date recorded in the Bmi object.
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Split user input into Bmi command, height, weight and date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Bmi parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] splitBmiInput(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[HealthConstant.NUM_BMI_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                || !input.contains(HealthConstant.HEIGHT_FLAG)
                || !input.contains(HealthConstant.WEIGHT_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }
        results[0] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEALTH_FLAG);
        results[1] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEIGHT_FLAG);
        results[2] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.WEIGHT_FLAG);
        results[3] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        return results;
    }

    //@@author j013n3
    /**
     * Calculates Bmi based on height and weight.
     *
     * @return The calculated Bmi value.
     * @throws AssertionError If calculated value is not positive.
     */
    public double calculateBmiValue() {
        double bmi = Math.round((weight / (Math.pow(height, UiConstant.POWER_OF_TWO))) * UiConstant.ROUNDING_FACTOR)
                / UiConstant.ROUNDING_FACTOR;
        assert bmi > 0: ErrorConstant.NEGATIVE_BMI_ERROR;
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
        assert bmiValue > 0: ErrorConstant.NEGATIVE_BMI_ERROR;

        if (bmiValue < HealthConstant.UNDERWEIGHT_BMI_THRESHOLD) {
            return HealthConstant.UNDERWEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.NORMAL_BMI_THRESHOLD) {
            return HealthConstant.NORMAL_WEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.OVERWEIGHT_BMI_THRESHOLD) {
            return HealthConstant.OVERWEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.OBESE_BMI_THRESHOLD) {
            return HealthConstant.OBESE_MESSAGE;
        } else {
            return HealthConstant.SEVERELY_OBESE_MESSAGE;
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
                + HealthConstant.BMI_MESSAGE_PREFIX
                + this.calculateBmiValue()
                + System.lineSeparator()
                + getBmiCategory(bmiValue);
    }
}
