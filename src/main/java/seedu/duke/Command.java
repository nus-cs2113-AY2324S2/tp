package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public abstract class Command {
    public abstract void execute(FavouritesList favourites, FoodList foods, ActivityList activities,
                                 Ui ui, Storage storage) throws FlirtForkException;

}
