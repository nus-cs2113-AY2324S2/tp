package seedu.voyagers.commands;

import seedu.voyagers.TripList;
import seedu.voyagers.Storage;
import seedu.voyagers.Ui;


public class EmptyCommand extends Command{

    public EmptyCommand(){
        super(new String[0]);
    }
    public void execute(TripList trips, Ui ui, Storage storage){
      //do nothing
    }
}
