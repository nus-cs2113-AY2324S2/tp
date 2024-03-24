package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class ViewHistoryCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        int foodCount = 0;
        int activityCount = 0;
        System.out.println("These are the activities you have completed:");
        for (int i=0; i<activities.size(); i++) {
            Activity oneActivity = activities.get(i);
            if (oneActivity.completionStatus.equals("C")) {
                System.out.println(++activityCount + ". " + oneActivity.description);
            }
        }
        System.out.println("");
        System.out.println("These are the restaurants you have visited:");
        for (int i=0; i<foods.size(); i++) {
            Food oneFood = foods.get(i);
            if (oneFood.completionStatus.equals("C")) {
                System.out.println(++foodCount + ". " + oneFood.description);
            }
        }
    }
}
