package workouts;

import java.util.ArrayList;

public class GymStation {
    protected String stationName;
    protected ArrayList<GymSet> sets;
    protected int numberOfSets;

    public GymStation(String name, Integer[] weightAndReps, int numberOfSets) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weightAndReps);
    }

    public void processSets(Integer[] weightAndReps){
        int weight = weightAndReps[0];
        int repitition = weightAndReps[1];
        GymSet newSet = new GymSet(weight, repitition);
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
}
