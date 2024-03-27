package workouts;

import utility.*;

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

    private void buildGymStationString(GymStation station, StringBuilder stringBuilder) {
        stringBuilder.append(station.getStationName());
    }

    private void buildGymSetString(GymStation station, StringBuilder stringBuilder) {
        stringBuilder.append(station.getNumberOfSets());
    }

    private void buildGymRepString(GymStation station, StringBuilder stringBuilder) {
        for (int j = 0; j < station.getNumberOfSets(); j++) {
            stringBuilder.append(station.getSets().get(j).getRepetitions());
            if (j != station.getNumberOfSets() - 1) {
                stringBuilder.append(",");
            }
        }
    }

    private void buildGymWeightString(GymStation station, StringBuilder stringBuilder) {
        for (int j = 0; j < station.getNumberOfSets(); j++) {
            stringBuilder.append(station.getSets().get(j).getWeight());
            if (j != station.getNumberOfSets() - 1) {
                stringBuilder.append(",");
            }
        }
    }


    public String getFormatForAllHistory(boolean isFirstIteration , int i) {


        StringBuilder gymDate = new StringBuilder();
        StringBuilder gymStationString = new StringBuilder();
        StringBuilder gymSetString = new StringBuilder();
        StringBuilder gymRepString = new StringBuilder();
        StringBuilder gymWeightString = new StringBuilder();
        if (date != null) {
            gymDate.append(date.toString());
        } else {
            gymDate.append(ErrorConstant.NO_DATE_SPECIFIED_ERROR);
        }

        GymStation station = getStations().get(i);
        buildGymStationString(station, gymStationString);
        buildGymSetString(station, gymSetString);
        buildGymRepString(station, gymRepString);
        buildGymWeightString(station, gymWeightString);


        if (isFirstIteration){
            return String.format(WorkoutConstant.HISTORY_ALL_DATA_FORMAT,
                    WorkoutConstant.GYM, gymDate,
                    UiConstant.DASH,
                    UiConstant.DASH,
                    UiConstant.DASH,
                    gymStationString,
                    gymSetString,
                    gymRepString,
                    gymWeightString);
        } else {
            return String.format(WorkoutConstant.HISTORY_ALL_DATA_FORMAT,
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

//    public String getFormatForAllHistoryFirst(){
//        String printedDate;
//
//        if (date != null) {
//            printedDate = date.toString();
//        } else {
//            printedDate = ErrorConstant.NO_DATE_SPECIFIED_ERROR;
//        }
////
//        StringBuilder gymStationString = new StringBuilder();
//        StringBuilder gymSetString = new StringBuilder();
//        StringBuilder gymRepString = new StringBuilder();
//        StringBuilder gymWeightString = new StringBuilder();
//
//        for(int i = 0; i < 1; i++){
//            GymStation station = stations.get(i);
//            gymStationString.append(station.getStationName());
//            gymSetString.append(station.getNumberOfSets());
//
//            for(int j = 0; j < station.getNumberOfSets(); j++){
//                gymRepString.append(station.getSets().get(j).getRepetitions());
//                gymWeightString.append(station.getSets().get(j).getWeight());
//                if(j != station.getNumberOfSets() - 1){
//                    gymWeightString.append(", ");
//                }
//            }
//
//
//        }
//
//
//        return String.format(WorkoutConstant.HISTORY_ALL_DATA_FORMAT,
//                WorkoutConstant.GYM,
//                printedDate,
//                "-",
//                "-",
//                "-",
//                gymStationString,
//                gymSetString,
//                gymRepString,
//                gymWeightString);
//     }

//    public String getFormatForAllHistorySubsequent(){
//        String printedDate;
//
//        if (date != null) {
//            printedDate = date.toString();
//        } else {
//            printedDate = ErrorConstant.NO_DATE_SPECIFIED_ERROR;
//        }
////
//        StringBuilder gymStationString = new StringBuilder();
//        StringBuilder gymSetString = new StringBuilder();
//        StringBuilder gymRepString = new StringBuilder();
//        StringBuilder gymWeightString = new StringBuilder();
//        if(stations.size() < 2){
//            return "";
//        }else{
//            for(int i = 1; i < stations.size(); i++){
//                GymStation station = stations.get(i);
//                gymStationString.append(station.getStationName());
//                gymSetString.append(station.getNumberOfSets());
//
//                for(int j = 0; j < station.getNumberOfSets(); j++){
//                    gymRepString.append(station.getSets().get(j).getRepetitions());
//                    gymWeightString.append(station.getSets().get(j).getWeight());
//                    if(j != station.getNumberOfSets() - 1){
//                        gymWeightString.append(", ");
//                    }
//                }
//            }
//        }
//
//
//
//        return String.format(WorkoutConstant.HISTORY_ALL_DATA_FORMAT,
//                "",
//                "",
//                "",
//                "",
//                "",
//                gymStationString,
//                gymSetString,
//                gymRepString,
//                gymWeightString);
//    }
}
