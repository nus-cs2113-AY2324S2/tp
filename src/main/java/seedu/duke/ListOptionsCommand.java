package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class ListOptionsCommand extends Command {
    private String optionType;

    public ListOptionsCommand(String optionType) {
        this.optionType = optionType;
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities,
                        Ui ui, Storage storage) throws FlirtForkException {
        switch(optionType) {
        case "food":
            printFoodList(ui, foods);
            break;
        case "activities":
            printActivityList(ui, activities);
            break;
        default:
            System.out.println("Invalid option! Please choose 'food' or 'activities'.");
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
}
