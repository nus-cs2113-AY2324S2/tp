package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class AddFoodCommand extends Command{
    private String description;
    public AddFoodCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails) throws FlirtForkException {
        Food food = new Food(description, "default location", "default price", "default cuisine", "U");
        favourites.addFavourite(food);
        System.out.println("Cupid's arrow strikes! This is now in your favourites. \n" + food);
        ui.showFavourite("Guess what? You've collected " + favourites.getFavourites().size() +
                " romantic treasures!");
    }
}
