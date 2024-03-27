package seedu.duke.commands;

import seedu.duke.ActivityList;
import seedu.duke.Command;
import seedu.duke.Favourites;
import seedu.duke.FavouritesList;
import seedu.duke.FoodList;
import seedu.duke.GiftList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.UserDetails;
import seedu.duke.exceptions.FlirtForkException;

public class DeleteFavouritesCommand extends Command {
    private final int index;
    public DeleteFavouritesCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        if (index >= 0 && index < favourites.getFavourites().size()) {
            Favourites removedFavourite = favourites.getFavourites().get(index);
            favourites.deleteFavourite(index);
            ui.showFavourite("Poof! Sayonara~ This favourite has been removed: \n" + removedFavourite);
            ui.showFavourite("Now you have " + favourites.getFavourites().size() + " romantic treasures!\n" +
                    "Your journey of love and taste continues~");
        } else {
            ui.errorMessage("This number's playing hard to get. How about trying one that is in range?");
        }
    }
}
