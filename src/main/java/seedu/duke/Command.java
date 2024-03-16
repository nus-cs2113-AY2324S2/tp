package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public abstract class Command {
    public abstract void execute(FavouritesList favourites, Ui ui, Storage storage) throws FlirtForkException;

}
