package workouts;

import utility.CustomExceptions;
import utility.Constant;
import utility.Parser;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gym extends Workout{
    protected LocalDate date = null;
    protected ArrayList<GymStation> stations = new ArrayList<>();

    // takes (station name, weight, sets, reps)

    public Gym() {

    }
    // overloaded constructor for optional date
    public Gym(String stringDate) {
        this.date = Parser.parseDate(stringDate);
    }

    /**
     * Adds station to an ArrayList of GymStation object
     * Takes string name, weight done, number of sets, number of reps
     * @param name
     * @param stringWeight
     * @param stringNumberOfSet
     * @param stringReps
     * @throws CustomExceptions.InvalidInput
     */
    public void addStation(String name, String stringWeight, String stringNumberOfSet,
                           String stringReps) throws CustomExceptions.InvalidInput{
        try {
            int reps = Integer.parseInt(stringReps);
            int weight = Integer.parseInt(stringWeight);
            int numberOfSets = Integer.parseInt(stringNumberOfSet);
            Integer[] weightAndReps = {weight, reps};
            GymStation newStation = new GymStation(name, weightAndReps, numberOfSets);
            stations.add(newStation);
        } catch (Exception e) {
            throw new CustomExceptions.InvalidInput(Constant.INVALID_GYM_INPUT);
        }
    }

    /**
     * Get specific station as part of Gym object based on workout.
     * @return
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

    @Override
    public String toString() {
        // to be implemented
        return null;
    }
}
