package seedu.voyagers.commands;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;
import seedu.voyagers.classes.Trip;

public class AddTripCommand extends Command{

    /**
     * Constructor for AddTripCommand
     * @param args: args[0]= name, args[1]= location, args[2]= start date,
     *            args[3]= end date, args[4]= description
     */
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
