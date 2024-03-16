package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;


public class AddActivityCommand extends Command{
    private String description;
    public AddActivityCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(FavouritesList favourites, Ui ui, Storage storage) throws FlirtForkException {
        Activity activity = new Activity(description);
        favourites.addFavourite(activity);
        System.out.println("Cupid's arrow strikes! This is now in your favourites. \n" + activity);
        ui.showFavourite("Guess what? You've collected " + favourites.getFavourites().size() +
                " romantic treasures!");
    }
}
