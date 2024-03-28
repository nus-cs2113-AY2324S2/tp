package seedu.duke.commands;

import seedu.duke.ActivityList;
import seedu.duke.Command;
import seedu.duke.FavouritesList;
import seedu.duke.FoodList;
import seedu.duke.GiftList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.UserDetails;
import seedu.duke.exceptions.FlirtForkException;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {

        try {
            storage.saveFavourites(favourites.getFavourites());
            ui.exitMessage();
        } catch (IOException e) {
            ui.errorMessage("Yikes! Our love potion spilled and couldn't save your data! " + e.getMessage());
        }
    }
}
