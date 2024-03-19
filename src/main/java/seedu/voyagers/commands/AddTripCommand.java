package seedu.voyagers.commands;

import seedu.voyagers.Storage;
import seedu.voyagers.Trip;
import seedu.voyagers.TripList;
import seedu.voyagers.Ui;

public class AddTripCommand extends Command{

    public AddTripCommand(String[] args){
        super(args);
    }

    public void execute(TripList trips, Ui ui, Storage storage){

        try{
            Trip trip = new Trip(args);
            trips.add(trip);
            ui.echo("Got it. I've added this trip:\n" + trip
                    + "\nNow you have " + trips.size() +
                    " trips in the list.");
        } catch (Exception e){
            ui.echo(e.getMessage());
        }



        //storage.writeTripFile(trips, trips.size());
    }

}
