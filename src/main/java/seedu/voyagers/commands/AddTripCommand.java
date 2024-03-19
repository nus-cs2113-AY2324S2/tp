package seedu.voyagers.commands;

import seedu.voyagers.Storage;
import seedu.voyagers.Trip;
import seedu.voyagers.TripList;
import seedu.voyagers.Ui;

import seedu.voyagers.Util;

import java.util.Date;


public class AddTripCommand extends Command{

    public AddTripCommand(String[] args){
        super(args);
    }

    public void execute(TripList trips, Ui ui, Storage storage){


        int l = args.length;
        System.out.println(args.length);
        for (int i = 0; i < l; i++){
            System.out.println(args[i]);
        }

        String name = args[0];
        Date startDate = null;
        Date endDate = null;
        try{
            startDate =  Util.dateFormat.parse(args[1]);
            endDate =   Util.dateFormat.parse(args[2]);
        } catch (Exception e){
            ui.echo("Invalid date format");
            return;
        }


        String location = args[3];
        String description = args[4];
        Trip trip = new Trip(name, startDate, endDate, location, description);


        try{
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
