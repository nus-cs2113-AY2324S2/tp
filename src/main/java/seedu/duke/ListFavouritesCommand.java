package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class ListFavouritesCommand extends Command{
    @Override
    public void execute(FavouritesList favourites, Ui ui, Storage storage) throws FlirtForkException {
        if (favourites.isEmpty()) {
            ui.showFavourite("No treasures found this time. Let's fill it with some love!");
        } else {
            ui.listFavourites(favourites);
        }
    }
}
