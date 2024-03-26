package workouts;

import utility.CustomExceptions;
import utility.Parser;
import utility.ErrorConstant;
import utility.WorkoutConstant;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a Gym object that contains an ArrayList of GymStation objects.
 */
public class Gym extends Workout {
    protected LocalDate date = null;
    protected ArrayList<GymStation> stations = new ArrayList<>();

    /**
     * Constructor that adds a Gym object to WorkoutList.
     */
    public Gym() {
        WorkoutList.addGym(this);
    }

    /**
     * Overloaded constructor that adds a Gym object to WorkoutList, and also takes the optional date parameter.
     *
     * @param stringDate String representing the date parameter specified.
     */
    public Gym(String stringDate) {
        this.date = Parser.parseDate(stringDate);
        WorkoutList.addGym(this);
    }

    /**
     * Adds station to an ArrayList of GymStation object.
     *
     * @param name        Name of the gym station.
     * @param weight      Weight used for the station.
     * @param numberOfSet Number of sets done.
     * @param repetitions Number of repetitions done.
     * @throws CustomExceptions.InvalidInput If there is invalid input in any parameter.
     */
    public void addStation(String name, int weight, int numberOfSet,
                           int repetitions) throws CustomExceptions.InvalidInput {
        try {
            GymStation newStation = new GymStation(name, weight, repetitions, numberOfSet);
            stations.add(newStation);
        } catch (Exception e) {
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_GYM_INPUT);
        }
    }

    /**
     * Get specific station as part of Gym object based on workout.
     *
     * @return The desired GymStation object.
     */
    public ArrayList<GymStation> getStations() {
        return stations;
    }

    public GymStation getStationByIndex(int index) throws CustomExceptions.OutOfBounds {
        if (index >= stations.size() || index < 0) {
            throw new CustomExceptions.OutOfBounds(WorkoutConstant.INVALID_GYM_STATION_INDEX);
        }
        return stations.get(index);
    }

    /**
     * Adds new gym station using validated parameters.
     *
     * @param validatedInputs Array representing validated GymStation parameters.
     * @param gym             Gym object to add the GymStation to.
     * @throws CustomExceptions.InsufficientInput If there is not enough parameters specified.
     * @throws CustomExceptions.InvalidInput      If there is invalid input.
     */
    public static void addGymStationInput(String[] validatedInputs, Gym gym) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        String exerciseName = validatedInputs[WorkoutConstant.INDEX_OF_STATION_NAME];
        int weights = Integer.parseInt(validatedInputs[WorkoutConstant.INDEX_OF_STATION_WEIGHTS]);
        int numberOfSets = Integer.parseInt(validatedInputs[WorkoutConstant.INDEX_OF_STATION_SETS]);
        int repetition = Integer.parseInt(validatedInputs[WorkoutConstant.INDEX_OF_STATION_REPS]);
        gym.addStation(exerciseName, weights, numberOfSets, repetition);

    }

    /**
     * Method checks if Gym values is valid
     * Returns {@code true} if {@code numberOfStation} parameters is valid.
     * Valid only if {@code numberOfStation} is a positive integer / not blank / and is digit.
     * Otherwise, throw {@code CustomExceptions.InvalidInput}  or {@code CustomExceptions.InsufficientInput}
     *
     * @param numberOfStation String representing the number of Station
     * @return {@code true} if all parameters are valid.
     */

    public static boolean checkIfGymIsValid(String numberOfStation) throws CustomExceptions.InvalidInput {
        if (numberOfStation.isBlank()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_CANNOT_BE_BLANK_ERROR);
        }

        try {
            int value = Integer.parseInt(numberOfStation);
            if (value <= 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_MUST_BE_POSITIVE_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_MUST_BE_DIGIT_ERROR);
        }

        return true;
    }

    /**
     * Retrieves the string representation of a Gym object.
     *
     * @return A formatted string representing the Gym object, inclusive of the date and gym stations done.
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null) {
            printedDate = date.toString();
            return String.format(" on %s", printedDate);
        } else {
            return "";
        }
    }
}
