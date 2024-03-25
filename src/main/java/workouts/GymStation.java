package workouts;

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
     * @param name String name of the station
     * @param weight Weight used.
     * @param repetition Number of reps done.
     * @param numberOfSets Number of sets done.
     */
    public GymStation(String name, int weight, int repetition , int numberOfSets) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weight, repetition);
    }

    /**
     * Function which adds a GymSet object to GymStation.
     * @param weight The weight done for the set.
     * @param repetition The number of repetitions done for the set.
     */
    public void processSets(int weight, int repetition){
        for (int i = 0; i < numberOfSets; i++) {
            GymSet newSet = new GymSet(weight, repetition);
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
    public  ArrayList<GymSet> getSets() {
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
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public static String[] checkIfGymStationInputValid(String[] inputs) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        String exerciseName = inputs[WorkoutConstant.INDEX_OF_STATION_NAME].trim();
        String sets = inputs[WorkoutConstant.INDEX_OF_STATION_SETS].split(UiConstant.SPLIT_BY_COLON)[1].trim();
        String reps = inputs[WorkoutConstant.INDEX_OF_STATION_REPS].split(UiConstant.SPLIT_BY_COLON)[1].trim();
        String weights = inputs[WorkoutConstant.INDEX_OF_STATION_WEIGHTS].split(UiConstant.SPLIT_BY_COLON)[1].trim();

        try {
            int setInteger = Integer.parseInt(sets);
            int repInteger = Integer.parseInt(reps);
            int weightInteger = Integer.parseInt(weights);
            assert setInteger > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;
            assert repInteger > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;
            assert weightInteger > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;

        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(WorkoutConstant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }


        return new String[]{exerciseName, sets, reps, weights};
    }
    /**
     * Retrieves the string representation of a GymStation object.
     *
     * @return A formatted string representing a GymStation object.
     */
    @Override
    public String toString() {
        return String.format(WorkoutConstant.GYM_STATION_FORMAT,
                this.getStationName()) + String.format(WorkoutConstant.INDIVIDUAL_GYM_STATION_FORMAT,
                this.getNumberOfSets(),
                this.getSpecificSet(0));
    }

}
