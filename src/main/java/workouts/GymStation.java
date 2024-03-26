package workouts;

import ui.Handler;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.UiConstant;
import utility.WorkoutConstant;

import java.util.ArrayList;

/**
 * Represents a GymStation object.
 */
public class GymStation {
    protected String stationName;
    protected ArrayList<GymSet> sets = new ArrayList<>();
    protected int numberOfSets;

    /**
     * Constructs a new GymStation object that contains the name, weight, number of repetitions and number of sets done
     * in one station.
     *
     * @param name         String name of the station
     * @param weightsList  Weight used.
     * @param repetition   Number of reps done.
     * @param numberOfSets Number of sets done.
     */
    public GymStation(String name, ArrayList<Integer> weightsList, int repetition, int numberOfSets) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weightsList, repetition);
    }

    /**
     * Function which adds a GymSet object to GymStation.
     *
     * @param weightsList     The weight done for the particular set.
     * @param repetition The number of repetitions done for the particular set.
     */
    public void processSets(ArrayList<Integer> weightsList, int repetition) {
        for (int i = 0; i < numberOfSets; i++) {
            GymSet newSet = new GymSet(weightsList.get(i), repetition);
            sets.add(newSet);
        }
    }

    /**
     * Retrieves the station name for the GymStation object.
     *
     * @return String representing the name for the station.
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Retrieves an ArrayList of gym sets for the GymStation object.
     *
     * @return The ArrayList of GymSet objects.
     */
    public ArrayList<GymSet> getSets() {
        return sets;
    }

    /**
     * Retrieves a specific GymSet using an index.
     *
     * @param index Index of the desired GymSet.
     * @return Desired GymSet object.
     */
    public GymSet getSpecificSet(int index) {
        return sets.get(index);
    }


    /**
     * Retrieves the number sets within the GymStation.
     *
     * @return The number of sets done.
     */
    public int getNumberOfSets() {
        return numberOfSets;
    }

    /**
     * Checks parameters from user input for adding a new GymStation.
     *
     * @param inputs List of strings representing user input.
     * @return Array of strings representing the parameters required for a new GymStation.
     * @throws CustomExceptions.InsufficientInput If there is not enough parameters specified.
     * @throws CustomExceptions.InvalidInput      If there is invalid input.
     */
    public static void AddGymStationInputValid(Gym gym, String inputs) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        String exerciseName = inputs.split(UiConstant.SPLIT_BY_SLASH)[WorkoutConstant.INDEX_OF_STATION_NAME].trim();
        String sets = Handler.extractSubstringFromSpecificIndex(inputs, WorkoutConstant.SPLIT_BY_SETS);
        String reps = Handler.extractSubstringFromSpecificIndex(inputs, WorkoutConstant.SPLIT_BY_REPS);
        String weights = Handler.extractSubstringFromSpecificIndex(inputs, WorkoutConstant.SPLIT_BY_WEIGHTS);

        String validExerciseName = checkIfExerciseNameIsValid(exerciseName);
        int setsInteger = checkIfSetsAreValid(sets);
        int repsInteger = checkIfRepsAreValid(reps);
        ArrayList<Integer> weightsArray = checkIfWeightsAreValid(weights, Integer.parseInt(sets));


        assert Integer.parseInt(sets) > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;
        assert Integer.parseInt(reps) > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;

        gym.addStation(exerciseName, weightsArray, setsInteger, repsInteger);
    }

    /**
     * Retrieves the string representation of a GymStation object.
     *
     * @return A formatted string representing a GymStation object.
     */
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder(String.format(WorkoutConstant.GYM_STATION_FORMAT,
                this.getStationName()) + String.format(WorkoutConstant.INDIVIDUAL_GYM_STATION_FORMAT,
                this.getNumberOfSets()));

        for (int i = 0; i < this.getNumberOfSets(); i++) {
            returnString.append(System.lineSeparator());
            returnString.append(String.format("\t- Set %d. %s", i+1 , this.getSets().get(i).toString()));
        }
        return returnString.toString();
    }

    private static String checkIfExerciseNameIsValid(String exerciseName) throws CustomExceptions.InsufficientInput {
        if (exerciseName.isBlank()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.GYM_EXERCISE_NAME_BLANK_ERROR);
        }
        return exerciseName;
    }

    private static int checkIfSetsAreValid(String sets) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        if (sets.isBlank()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.GYM_SET_BLANK_ERROR);
        }
        int setInteger = 0;
        try {
            setInteger = Integer.parseInt(sets);
            if (setInteger <= 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_SET_DIGIT_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return setInteger;
    }

    private static int checkIfRepsAreValid(String reps) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        if (reps.isBlank()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.GYM_REP_BLANK_ERROR);
        }

        int repInteger = 0;
        try {
            repInteger = Integer.parseInt(reps);
            if (repInteger <= 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_REP_POSITIVE_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_REP_DIGIT_ERROR);
        }

        return repInteger;
    }

    private static ArrayList<Integer> checkIfWeightsAreValid(String weights, int sets) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        if(weights.isBlank()){
            throw new CustomExceptions.InsufficientInput(ErrorConstant.GYM_WEIGHT_BLANK_ERROR);
        }
        String [] weightsArray = weights.split(UiConstant.SPLIT_BY_COMMAS);

        // if they give me too many values
        if(weightsArray.length != sets){
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHTS_INCORRECT_NUMBER_ERROR);
        }

        return getValidatedWeightsArray(weightsArray);
    }

    private static ArrayList<Integer> getValidatedWeightsArray(String[] weightsArray) throws CustomExceptions.InvalidInput {
        ArrayList<Integer> validatedWeightsArray = new ArrayList<>();

        try{
            for(String weight: weightsArray){
                int weightInteger = Integer.parseInt(weight);
                if (weightInteger < 0){
                    throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHT_POSITIVE_ERROR);
                }
                validatedWeightsArray.add(weightInteger);
            }
        } catch (NumberFormatException e){
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHT_DIGIT_ERROR);
        }
        return validatedWeightsArray;
    }
}


