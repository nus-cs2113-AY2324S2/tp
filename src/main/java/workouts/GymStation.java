package workouts;

import utility.UiConstant;
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
     * Retrieves the string representation of a GymStation object.
     *
     * @return A formatted string representing a GymStation object.
     */
    @Override
    public String toString() {
        return String.format(UiConstant.GYM_STATION_FORMAT,
                this.getStationName()) + String.format(UiConstant.INDIVIDUAL_GYM_STATION_FORMAT,
                this.getNumberOfSets(),
                this.getSpecificSet(0));
    }

}
