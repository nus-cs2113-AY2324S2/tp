package health;

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

        assert userInputs.length > 0 : "Number of userInputs parts should be greater than 0";

        String healthType = userInputs[UiConstant.HEALTH_TYPE_INDEX].trim();

        if (healthType.isBlank()){
            throw new CustomExceptions.InvalidInput(UiConstant.BLANK_INPUT_FOR_HEALTH);
        }

        healthType = healthType.toLowerCase();

        boolean isBmi = healthType.equals(UiConstant.BMI_INPUT);
        boolean isPeriod = healthType.equals(UiConstant.PERIOD_INPUT);

        if(!isBmi && !isPeriod){
            throw new CustomExceptions.InvalidInput(UiConstant.INVALID_INPUT_FOR_HEALTH);
        }

        if(isBmi && userInputs.length < UiConstant.BMI_PARAMETERS){
            throw new CustomExceptions.InsufficientInput(UiConstant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        if(isPeriod && userInputs.length < UiConstant.BMI_PARAMETERS){
            throw new CustomExceptions.InsufficientInput(UiConstant.INSUFFICIENT_PARAMETERS_FOR_PERIOD);
        }

        assert userInputs.length == 5 : "Array of userInputs should have 5 elements";

        if (isBmi){
            return UiConstant.BMI;
        } else {
            return UiConstant.PERIOD;
        }
    }
}
