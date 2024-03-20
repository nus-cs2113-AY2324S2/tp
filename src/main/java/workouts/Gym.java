package workouts;

import utility.CustomExceptions;
import utility.Constant;
import utility.Parser;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a Gym object that contains an ArrayList of GymStation objects.
 */
public class Gym extends Workout{
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
     * @param stringDate String representing the date parameter specified.
     */
    public Gym(String stringDate) {
        this.date = Parser.parseDate(stringDate);
        WorkoutList.addGym(this);
    }

    /**
     * Adds station to an ArrayList of GymStation object.
     *
     * @param name Name of the gym station.
     * @param weight Weight used for the station.
     * @param numberOfSet Number of sets done.
     * @param repetitions Number of repetitions done.
     * @throws CustomExceptions.InvalidInput If there is invalid input in any parameter.
     */
    public void addStation(String name, int weight, int numberOfSet,
                           int repetitions) throws CustomExceptions.InvalidInput{
        try {
            GymStation newStation = new GymStation(name, weight, repetitions, numberOfSet);
            stations.add(newStation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomExceptions.InvalidInput(Constant.INVALID_GYM_INPUT);
        }
    }

    /**
     * Get specific station as part of Gym object based on workout.
     *
     * @return The desired GymStation object.
     */
    public ArrayList<GymStation> getStations(){
        return stations;
    }
    public GymStation getStationByIndex(int index) throws CustomExceptions.OutOfBounds{
        if (index >= stations.size() || index < 0) {
            throw new CustomExceptions.OutOfBounds(Constant.INVALID_GYM_STATION_INDEX);
        }
        return stations.get(index);
    }

    /**
     * Retrieves the string representation of a Gym object.
     *
     * @return A formatted string representing the Gym object, inclusive of the date and gym stations done.
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null){
            printedDate = date.toString();
            return String.format("(%s)", printedDate);
        } else{
            return "";
        }
    }
}
