package health;

import utility.Constant;
import utility.CustomExceptions;

/*
 * The Health class represents health information of the user.
 */
public class Health {

    public static String checkTypeOfHealth(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] userInputs = userInput.split("/"); // Constant.SPLIT_BY_SLASH = "/"

        String healthType = userInputs[Constant.HEALTH_TYPE_INDEX].trim(); // Constant.EXERCISE_TYPE_INDEX = 1

        if (healthType.isBlank()){
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_HEALTH);
        }

        healthType = healthType.toLowerCase();


        boolean isBmi = healthType.equals(Constant.BMI_INPUT);
        boolean isPeriod = healthType.equals(Constant.PERIOD_INPUT);
        if(!isBmi && !isPeriod){
            throw new CustomExceptions.InvalidInput(Constant.INVALID_INPUT_FOR_EXERCISE);
        }

        if(isBmi && userInputs.length < 3){
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        if(isPeriod && userInputs.length < 3){
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_PERIOD);
        }

        if (isBmi){
            return Constant.BMI;
        } else {
            return Constant.PERIOD;
        }
    }
}
