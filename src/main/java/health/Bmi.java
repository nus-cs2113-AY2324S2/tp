package health;

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

        assert this.height > 0 && this.weight > 0 : HealthConstant.HEIGHT_WEIGHT_REQUIRE_POSITIVE;

        this.date = Parser.parseDate(date);

        this.bmiValue = calculateBmiValue();
        this.bmiCategory = getBmiCategory(bmiValue);
    }

    //@@author syj02
    /**
     * Split user input into Bmi command, height, weight and date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Bmi parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] getBmi(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[HealthConstant.BMI_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                || !input.contains(HealthConstant.HEIGHT_FLAG)
                || !input.contains(HealthConstant.WEIGHT_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        int indexH = input.indexOf(HealthConstant.HEALTH_FLAG);
        int indexHeight = input.indexOf(HealthConstant.HEIGHT_FLAG);
        int indexWeight = input.indexOf(HealthConstant.WEIGHT_FLAG);
        int indexDate = input.indexOf(HealthConstant.DATE_FLAG);

        String command = input.substring(indexH + HealthConstant.BMI_H_OFFSET, indexHeight).trim();
        String heightSubstring = input.substring(indexHeight + HealthConstant.BMI_HEIGHT_OFFSET, indexWeight).trim();
        String weightSubstring = input.substring(indexWeight + HealthConstant.BMI_WEIGHT_OFFSET, indexDate).trim();
        String dateSubstring = input.substring(indexDate + HealthConstant.DATE_OFFSET).trim();

        if (command.isEmpty() || heightSubstring.isEmpty() || weightSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(HealthConstant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        results[0] = command;
        results[1] = heightSubstring;
        results[2] = weightSubstring;
        results[3] = dateSubstring;

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
        assert bmi > 0: HealthConstant.BMI_MUST_BE_POSITIVE;
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
        assert bmiValue > 0: HealthConstant.BMI_MUST_BE_POSITIVE;

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
