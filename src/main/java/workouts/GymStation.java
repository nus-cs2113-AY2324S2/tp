package workouts;

import utility.Constant;

import java.util.ArrayList;
import java.util.HashMap;

public class GymStation {
    protected String stationName;
    protected ArrayList<GymSet> sets = new ArrayList<GymSet>();
    protected int numberOfSets;

    /**
     * Gym Station contains an ArrayList of GymSets.
     * @param name
     * @param weight
     * @param repetition
     * @param numberOfSets
     */
    public GymStation(String name, int weight, int repetition , int numberOfSets) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weight, repetition);
    }

    public void processSets(int weight, int repetition){

        // to implement later different weights
        for (int i = 0; i < repetition; i++) {
            GymSet newSet = new GymSet(weight, repetition);
            sets.add(newSet);
        }

    }

    public String getStationName() {
        return stationName;
    }

    public  ArrayList<GymSet> getSets() {
        return sets;
    }

    public GymSet getSpecificSet(int index) {
        return sets.get(index);
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    @Override
    public String toString() {
        ArrayList<GymSet> allSets = this.getSets();
        HashMap<String, Integer> allVariations = new HashMap<>();

        for (GymSet set : allSets) {
            if (allVariations.containsKey(set.toString())) {
                allVariations.put(set.toString(), allVariations.get(set.toString()) + 1);
            } else {
                allVariations.put(set.toString(), 1);
            }
        }
        StringBuilder format = new StringBuilder(String.format(Constant.GYM_STATION_FORMAT,
                this.getStationName()));

        for (String key : allVariations.keySet()) {
            format.append(String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, this.getNumberOfSets(), key));
        }

        return format.toString();
    }
}
