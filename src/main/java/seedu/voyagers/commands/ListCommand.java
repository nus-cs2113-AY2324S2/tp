package seedu.voyagers.commands;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;

public class ListCommand extends Command{


    public void execute(TripList trips, Ui ui, Storage storage){

        if (trips.size() == 0){
            ui.echo("You have no trips in your list.");
            return;
        }

        ui.echo("Here are the trips in your list:");
        for (int i = 0; i < trips.size(); i++){
            ui.echo((i + 1) + ". " + trips.get(i));
        }
    }
}
