package seedu.voyagers.commands;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;
import seedu.voyagers.classes.Trip;

import java.util.Date;

public class ModifyTripCommand extends Command{

    public ModifyTripCommand(String[] args){
        super(args);
    }

    public void execute(TripList trips, Ui ui, Storage storage){

        String oldName = args[0];
        String modificationType = args[1];

        int index = trips.findTrip(oldName);
        if (index == -1){
            ui.echo("No such trip found");
            return;
        }

        Trip trip = trips.get(index);

        switch (modificationType){
        case "name":
            if (trips.findTrip(args[2]) != -1){
                ui.echo("A trip with that name already exists");
                return;
            }
            trip.setName(args[2]);
            break;
        case "dates":
            try{
                trip.setDates(new Date(args[2]), new Date(args[3]));
            } catch (Exception e){
                //TODO: be more descriptive about the error (e.g. invalid date format,
                // start date after end date, etc.)
                ui.echo("Invalid dates");
                return;
            }
        case "location":
            trip.setLocation(args[2]);
            break;
        case "description":
            trip.setDescription(args[2]);
            break;
        default:
            ui.echo("Invalid modification type");
            return;
        }

        ui.echo("Noted. I've modified this trip:\n" + trip);
    }
}
