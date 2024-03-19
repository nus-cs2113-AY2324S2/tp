package seedu.voyagers.commands;

import seedu.voyagers.Storage;
import seedu.voyagers.Trip;
import seedu.voyagers.TripList;
import seedu.voyagers.Ui;

import java.text.DateFormat;
import java.util.Date;


public class AddTrip extends Command{

    public AddTrip(String[] args){
        super(args);
    }

    public void execute(TripList trips, Ui ui, Storage storage){
        String name = args[0];
        Date startDate = new Date(args[1]);
        Date endDate = new Date(args[2]);
        String location = args[3];
        String description = args[4];
        Trip trip = new Trip(name, startDate, endDate, location, description);
        trips.add(trip);
        ui.echo("Got it. I've added this trip:\n" + trip
                + "\nNow you have " + trips.size() +
                " trips in the list.");

        //storage.writeTripFile(trips, trips.size());
    }

}
