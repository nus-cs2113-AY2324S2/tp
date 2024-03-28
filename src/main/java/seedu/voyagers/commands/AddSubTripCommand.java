package seedu.voyagers.commands;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;
import seedu.voyagers.classes.Trip;

//TODO: check dates make sense with main trip
public class AddSubTripCommand extends Command{

    public AddSubTripCommand(String[] args){
        super(args);
    }

    public void execute(TripList trips, Ui ui, Storage storage){

        Trip mainTrip = null;
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);

        try{
            mainTrip = trips.getTrip(args[0]);
        } catch (Exception e){
            ui.echo(e.getMessage());
            return;
        }

        try{
            Trip trip = new Trip(newArgs);
            mainTrip.addSubTrip(trip);
            ui.echo("Got it. I've added this trip as a subtrip of :" + mainTrip.getName() + "\n" + trip
                    + "\nNow you have " + mainTrip.getSubTripsSize() +
                    " subtrips.");
        } catch (Exception e){
            ui.echo(e.getMessage());
        }

        //storage.writeTripFile(trips, trips.size());
    }
}
