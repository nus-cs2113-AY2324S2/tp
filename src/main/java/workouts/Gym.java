package workouts;

import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.Parser;
import utility.UiConstant;
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
     * @param weightsList Weights used for the station.
     * @param numberOfSet Number of sets done.
     * @param repetitions Number of repetitions done.
     * @throws CustomExceptions.InvalidInput If there is invalid input in any parameter.
     */
    public void addStation(String name, ArrayList<Integer> weightsList, int numberOfSet,
                           int repetitions) throws CustomExceptions.InvalidInput {
        try {
            GymStation newStation = new GymStation(name, weightsList, repetitions, numberOfSet);
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
            throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_BLANK_ERROR);
        }

        try {
            int value = Integer.parseInt(numberOfStation);
            if (value <= 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_POSITIVE_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.NO_OF_STATION_DIGIT_ERROR);
        }

        return true;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
            return String.format(" (Date: %s)", printedDate);
        } else {
            return " (Date: NA)";
        }
    }


    /**
     * Use when printing the workout history. This method is used to format the reps and weights into a string.
     *
     * @param station The GymStation object which contains the sets to be formatted.
     * @return A StringBuilder array where [0] is reps, [1] is weights.
     */
    private StringBuilder[] buildGymRepAndWeightString(GymStation station){
        StringBuilder[] repAndWeightArray = new StringBuilder[2];
        repAndWeightArray[0] = new StringBuilder();
        repAndWeightArray[1] = new StringBuilder();


        int repIndex = 0;
        int weightIndex = 1;

        ArrayList<GymSet> gymSets = station.getSets();
        for (int i = 0; i < gymSets.size(); i++) {
            String gymRepString = String.valueOf(gymSets.get(i).getRepetitions());
            String gymWeightString = String.valueOf(gymSets.get(i).getWeight());

            repAndWeightArray[repIndex].append(gymRepString);
            repAndWeightArray[weightIndex].append(gymWeightString);
            if (i != gymSets.size() - 1) {
                repAndWeightArray[repIndex].append(UiConstant.COMMAS);
                repAndWeightArray[weightIndex].append(UiConstant.COMMAS);
            }
        }
        return repAndWeightArray;
    }


    /**
     * Used when printing all the workouts. This method takes in two parameters {@code isFirstIteration} and {@code i}
     * @param index indicates which particular gymStation is being queried.
     * @return
     */
    public String getHistoryFormatForSpecificGymStation(int index) {

        StringBuilder gymDate = new StringBuilder();
        if (date != null) {
            gymDate.append(date);
        } else {
            gymDate.append(ErrorConstant.NO_DATE_SPECIFIED_ERROR);
        }

        // Get the string format for a specific gym station
        GymStation station = getStations().get(index);
        String gymStationString = station.getStationName();
        String gymSetString = String.valueOf(station.getNumberOfSets());

        // Process the reps and weights into string format
        StringBuilder [] repAndWeightArray = buildGymRepAndWeightString(station);
        String gymRepString = repAndWeightArray[0].toString();
        String gymWeightString = repAndWeightArray[1].toString();

        // If it is first iteration, includes dashes for irrelevant field
        if (index == 0){
            return String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT,
                    WorkoutConstant.GYM, gymDate,
                    UiConstant.DASH,
                    UiConstant.DASH,
                    UiConstant.DASH,
                    gymStationString,
                    gymSetString,
                    gymRepString,
                    gymWeightString);
        } else {
            // if it is not, then leave it blank
            return String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT,
                    UiConstant.EMPTY_STRING,
                    UiConstant.EMPTY_STRING,
                    UiConstant.EMPTY_STRING,
                    UiConstant.EMPTY_STRING,
                    UiConstant.EMPTY_STRING,
                    gymStationString,
                    gymSetString,
                    gymRepString,
                    gymWeightString
            );

        }
    }

}
