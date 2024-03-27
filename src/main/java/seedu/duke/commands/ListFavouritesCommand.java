package seedu.duke.commands;

import seedu.duke.*;
import seedu.duke.exceptions.FlirtForkException;

public class ListFavouritesCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        if (favourites.isEmpty()) {
            ui.showFavourite("No treasures found this time. Let's fill it with some love!");
        } else {
            ui.listFavourites(favourites);
        }
    }
}
