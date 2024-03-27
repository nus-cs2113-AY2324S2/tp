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

import java.util.ArrayList;

public class FindFavouritesCommand extends Command {
    private String keyword;
    public FindFavouritesCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        ArrayList<Favourites> matchingFavourites = favourites.findFavourites(keyword);
        ui.showMatchingFavourites(matchingFavourites);
    }
}
