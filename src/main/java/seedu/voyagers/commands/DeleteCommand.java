package seedu.voyagers.commands;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;
import seedu.voyagers.classes.Trip;

public class DeleteCommand extends Command{

    public DeleteCommand(String[] args){
        super(args);
    }
    public void execute(TripList trips, Ui ui, Storage storage){
        int index = trips.findTrip(args[0]);

        if (index == -1){
            ui.echo("No such trip found");
            return;
        }

        Trip trip = trips.remove(index);
        ui.echo("Noted. I've removed this trip:\n" + trip
                + "\nNow you have " + trips.size() +
                " trips in the list.");
        //storage.writeTripFile(trips, trips.size());
    }

}
