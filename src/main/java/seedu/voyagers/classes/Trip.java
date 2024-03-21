package seedu.voyagers.classes;

import java.util.ArrayList;
import java.util.Date;
import seedu.voyagers.utils.FormatDate;


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

        assert startDate.before(endDate) : "Start date cannot be after end date";

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
    }

    public Trip(String[] args) throws Exception{
        this.name = args[0];

        this.startDate =  FormatDate.dateFormat.parse(args[1]);
        this.endDate =   FormatDate.dateFormat.parse(args[2]);
        this.location = args[3];
        this.description = args[4];

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

    public int getSubTripsSize(){
        return subTrips.size();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDates(Date startDate, Date endDate) {

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the sub-trips of the current trip.
     *
     * @return the list with sub-trips of the current trip
     */
    public ArrayList<Trip> getSubTrips() {
        return subTrips;
    }

    /**
     * Adds a sub-trip to the current trip.
     *
     * @param subTrip the sub-trip to be added
     */
    public void addSubTrip(Trip subTrip) {
        subTrips.add(subTrip);
    }

    /**
     * Removes the specified sub-trip from the current trip.
     *
     * @param subTrip the sub-trip to be removed
     */
    public void removeSubTrip(Trip subTrip) {
        subTrips.remove(subTrip);
    }

    /**
     * Removes the sub-trip at the specified index.
     *
     * @param i the index of the sub-trip to be removed. Index starts from 0.
     */
    public void removeSubTrip(int i) {
        try {
            subTrips.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Returns the sub-trip at the specified index.
     *
     * @param i the index of the sub-trip to be returned. Index starts from 0.
     * @return the sub-trip at the specified index
     */
    public Trip getSubTrip(int i) {
        try {
            return subTrips.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public String toString() {
        String s = "Name: " + name + "\t\tStart Date: " +
                FormatDate.dateFormat.format(startDate) + "\t\tEnd Date: " +
                FormatDate.dateFormat.format(endDate) + "\t\tLocation: " +
                location + "\t\tDescription: " + description;
        s += "\n\tSub-trips:";
        for (Trip t : subTrips) {
            s += "\n\t\t" + t.toString();
        }

        return s;

    }
}
