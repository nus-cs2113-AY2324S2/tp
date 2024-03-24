package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class HelpCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails) throws FlirtForkException {
        System.out.println("I know you are excited to Flirt & Fork :) Here's how: \n");
        ui.helpMessage();
    }
}
