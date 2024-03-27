package health;

import utility.ErrorConstant;
import utility.HealthConstant;
import utility.UiConstant;
import utility.CustomExceptions;

/**
 * Represents a Health object to track user's health information.
 */
public class Health {

    /**
     * Check the type of health operation requested based on user input.
     *
     * @param userInput A user-provided string, containing health type and its parameters.
     * @return A health type constant.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws AssertionError If user input or health type is null or
     *                        if the number of parameters is not equivalent to 4.
     */
    public static String checkTypeOfHealth(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {

        String[] userInputs = userInput.split(UiConstant.SPLIT_BY_SLASH);

        assert userInputs.length > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;

        String healthType = userInputs[HealthConstant.HEALTH_TYPE_INDEX].trim();

        if (healthType.isBlank()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.HEALTH_INPUT_BLANK_ERROR);
        }

        healthType = healthType.toLowerCase();

        boolean isBmi = healthType.equals(HealthConstant.BMI_INPUT);
        boolean isPeriod = healthType.equals(HealthConstant.PERIOD_INPUT);
        boolean isPredict = healthType.equals(HealthConstant.PREDICT_INPUT);
        boolean isAppointment = healthType.equals(HealthConstant.APPOINTMENT_INPUT);


        if (!isBmi && !isPeriod && !isPredict && !isAppointment) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HEALTH_INPUT_ERROR);
        }

        if (isBmi && userInputs.length < HealthConstant.NUM_BMI_PARAMETERS) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }

        if (isPeriod && userInputs.length < HealthConstant.NUM_PERIOD_PARAMETERS) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }

        if (isAppointment && userInputs.length < HealthConstant.NUM_APPOINTMENT_PARAMETERS) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }

        if (isBmi) {
            return HealthConstant.BMI;
        } else if (isPeriod) {
            return HealthConstant.PERIOD;
        } else if (isPredict){
            return HealthConstant.PREDICT;
        } else {
            return HealthConstant.APPOINTMENT;
        }
    }
}
