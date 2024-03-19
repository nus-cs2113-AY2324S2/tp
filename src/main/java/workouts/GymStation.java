package workouts;

import utility.Constant;

import java.util.ArrayList;

public class GymStation {
    protected String stationName;
    protected ArrayList<GymSet> sets;
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
        GymSet newSet = new GymSet(weight, repetition);
        sets.add(newSet);
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
        return "Test";// implement later
//        return String.format("%s \t\t%s",
//                this.getStationName(), this.getNumberOfSets(), this.get
    }
}
