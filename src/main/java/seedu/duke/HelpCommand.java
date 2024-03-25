package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class HelpCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        System.out.println("I know you are excited to Flirt & Fork :) Here's how: \n");
        ui.helpMessage();
        ui.showMessage("Send me 'help' if you're new!");
    }
}
