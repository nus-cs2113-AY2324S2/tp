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
     * @param weight
     * @param numberOfSet
     * @param repetitions
     * @throws CustomExceptions.InvalidInput
     */
    public void addStation(String name, int weight, int numberOfSet,
                           int repetitions) throws CustomExceptions.InvalidInput{
        try {
            GymStation newStation = new GymStation(name, weight, repetitions, numberOfSet);
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
        String printedDate;
        if (date != null){
            printedDate = date.toString();
        } else{
            printedDate = Constant.NO_DATE_SPECIFIED;
        }
        return String.format("%s \t\t%s",
                Constant.GYM, printedDate);
    }
}
