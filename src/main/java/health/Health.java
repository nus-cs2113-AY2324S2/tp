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

        if (healthType.isBlank()){
            throw new CustomExceptions.InvalidInput(HealthConstant.BLANK_INPUT_FOR_HEALTH);
        }

        healthType = healthType.toLowerCase();

        boolean isBmi = healthType.equals(HealthConstant.BMI_INPUT);
        boolean isPeriod = healthType.equals(HealthConstant.PERIOD_INPUT);

        if(!isBmi && !isPeriod){
            throw new CustomExceptions.InvalidInput(HealthConstant.INVALID_INPUT_FOR_HEALTH);
        }

        if(isBmi && userInputs.length < HealthConstant.BMI_PARAMETERS){
            throw new CustomExceptions.InsufficientInput(HealthConstant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        if(isPeriod && userInputs.length < HealthConstant.BMI_PARAMETERS){
            throw new CustomExceptions.InsufficientInput(HealthConstant.INSUFFICIENT_PARAMETERS_FOR_PERIOD);
        }

        assert userInputs.length == 5 : HealthConstant.NUMBER_OF_INPUTS_REQUIRED_PERIOD;

        if (isBmi){
            return HealthConstant.BMI;
        } else {
            return HealthConstant.PERIOD;
        }
    }
}
