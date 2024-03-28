package seedu.voyagers.utils;

import seedu.voyagers.classes.Profile;
import seedu.voyagers.classes.Trip;

import java.util.ArrayList;

public interface Payable {
    void addPeople(Profile[] people);
    void removePeople(Profile[] people);
    ArrayList<Profile> getPeople();
    void modifyPercentages(Double[] percentages);
    ArrayList<Double> getPercentages();
    void setTrip(Trip trip);
    Trip getTrip();

}
