package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class GenerateItineraryCommand extends Command{
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        Food food = foods.getRandomFood();
        Activity activity = activities.getRandomActivity();
        System.out.println("You can do " + activity + " and have a nice meal at " + food);
    }
}
