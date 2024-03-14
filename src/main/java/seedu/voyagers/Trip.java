package seedu.voyagers;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private String description;

    private ArrayList<Trip> subTrips = new ArrayList<>();

    public Trip(String name, Date startDate, Date endDate, String location, String description) {

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns the duration of the trip in days.
     * @return duration of the trip in days
     */
    public int getDuration() {
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the sub-trips of the current trip.
     * @return the array with sub-trips of the current trip
     */
    public ArrayList<Trip> getSubTrips() {
        return subTrips;
    }

    /**
     * Adds a sub-trip to the current trip.
     * @param subTrip the sub-trip to be added
     */
    public void addSubTrip(Trip subTrip) {
        subTrips.add(subTrip);
    }
}
