package seedu.voyagers.classes;
import seedu.voyagers.VoyagerException;
import seedu.voyagers.classes.Trip;

import java.util.ArrayList;


/**
 * Represents a list of trips
 */
public class TripList {
    private ArrayList<Trip> trips;


    public TripList(int size){
        this.trips = new ArrayList<Trip>(size);
    }

    public TripList(ArrayList<Trip> trips){
        this.trips = trips;
    }

    /**
     * Adds a trip to the list
     * @param trip the trip to add
     */
    public void add(Trip trip){
        this.trips.add(trip);
    }

    /**
     * Removes a trip from the list
     * @param index the index of the trip to remove. Index starts from 1
     * @return the trip that was removed
     * @throws VoyagerException if the index is invalid
     */
    public Trip remove (String index) throws VoyagerException{
        Trip trip = trips.get(checkIndex(index) - 1);
        this.trips.remove(checkIndex(index) - 1);
        return trip;
    }

    /**
     * Removes a trip from the list
     * @param index the index of the trip to remove. Index starts from 0
     * @return the trip that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Trip remove(int index) throws IndexOutOfBoundsException{
        Trip trip = trips.get(index);
        this.trips.remove(index);
        return trip;
    }

    /**
     * Returns the trip at the specified index
     * @param index the index of the trip to return. Index starts from 0
     * @return the trip at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Trip get(int index) throws IndexOutOfBoundsException{
        return this.trips.get(index);
    }

    /**
     * Returns the trip at the specified index
     * @param index the index of the trip to return. Index starts from 1
     * @return the trip at the specified index
     * @throws VoyagerException if the index is invalid
     */
    public Trip get(String index) throws VoyagerException{
        return this.trips.get(checkIndex(index) - 1);
    }

    /**
     * Tries to parse the index to an integer and checks if it is within the range of the list.
     * Index starts from 1.
     * @param index the index to check
     * @return the index as an integer
     */
    private int checkIndex(String index) throws VoyagerException{
        int i;
        try{
            i = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new VoyagerException("Please enter a valid number");
        }

        if (i > this.trips.size() || i < 1){
            throw new VoyagerException("Sorry. There are only " + trips.size() + " trips in the list");
        }

        return i;
    }



    public ArrayList<Trip> getTrips(){
        return this.trips;
    }

    /**
     * Returns the number of trips in the list
     * @return the number of trips in the list
     */
    public int size(){
        return this.trips.size();
    }
}

