package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class HelpCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        
        System.out.println("I know you’re excited to Flirt & Fork :) Here's how: \n");
        ui.helpMessage();
    }
}
