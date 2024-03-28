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

public class ListOptionsCommand extends Command {
    private String optionType;

    public ListOptionsCommand(String optionType) {
        this.optionType = optionType;
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities,
                        Ui ui, Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        switch(optionType) {
        case "food":
            printFoodList(ui, foods);
            break;
        case "activities":
            printActivityList(ui, activities);
            break;
        case "gifts":
            printGiftList(ui, gifts);
            break;
        default:
            throw new FlirtForkException("Invalid option! Please choose 'food', 'activities' or 'gifts'.");
        }
    }

    private void printFoodList(Ui ui, FoodList foods) {
        ui.listFood();
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(i + 1 + ". " + foods.get(i));
        }
    }

    private void printActivityList(Ui ui, ActivityList activities) {
        ui.listActivities();
        for (int i = 0; i < activities.size(); i++) {
            System.out.println(i + 1 + ". " + activities.get(i));
        }
    }

    private void printGiftList(Ui ui, GiftList gifts) {
        ui.listGifts();
        for (int i = 0; i < gifts.size(); i++) {
            System.out.println(i + 1 + ". " + gifts.get(i));
        }
    }
}
