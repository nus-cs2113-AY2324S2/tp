package workouts;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gym extends Workout{
    protected LocalDate date = null;
    protected ArrayList<GymStation> stations = new ArrayList<>();

    // takes (station name, weight, sets, reps)

    public Gym() {

    }
    public Gym(String stringDate) {
        this.date = parseDate(stringDate);

    }

    public void addStation(String name, String stringWeight, String stringNumberOfSet, String stringReps) {
        int reps = Integer.parseInt(stringReps);
        int weight = Integer.parseInt(stringWeight);
        int numberOfSets = Integer.parseInt(stringNumberOfSet);
        Integer[] weightAndReps = {weight, reps};
        GymStation newStation = new GymStation(name, weightAndReps, numberOfSets);
        stations.add(newStation);
    }

    public ArrayList<GymStation> getStations(){
        return stations;
    }

    public LocalDate getDate(){
        return date;
    }

    public GymStation getStationByIndex(int index) {
        return stations.get(index);
    }
}
