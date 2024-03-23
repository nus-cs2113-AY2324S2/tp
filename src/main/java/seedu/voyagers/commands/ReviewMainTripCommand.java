package seedu.voyagers.commands;

import seedu.voyagers.classes.Trip;
import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.utils.Storage;

public class ReviewMainTripCommand extends Command {

    public ReviewMainTripCommand(String[] args){
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
        assert mainTrip != null : "mainTrip cannot be null when setting review score";

        Integer score = Integer.parseInt(newArgs[0]);
        if(!(score >= 1 && score <= 5)){
            System.out.println("Review score is on a 1 - 5 scale. Invalid review score entered.");
            return;
        }

        try{
            mainTrip.setReviewScore(score);
        } catch (Exception e){
            ui.echo(e.getMessage());
        }
        System.out.println("Acknowledged, trip: '" + mainTrip.getName() + "' has a review score of " + score + ".");
    }

}
