package seedu.duke.commands;

import seedu.duke.Activity;
import seedu.duke.ActivityList;
import seedu.duke.Command;
import seedu.duke.FavouritesList;
import seedu.duke.Food;
import seedu.duke.FoodList;
import seedu.duke.Gift;
import seedu.duke.GiftList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.UserDetails;
import seedu.duke.exceptions.FlirtForkException;

public class ViewHistoryCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
        int foodCount = 0;
        int activityCount = 0;
        int giftCount = 0;

        System.out.println("These are the activities you have marked:");
        for (int i=0; i<activities.size(); i++) {
            Activity oneActivity = activities.get(i);
            String completionStatus = oneActivity.getCompletionStatus();
            if (completionStatus.equals("C")) {
                System.out.println(++activityCount + ". " + oneActivity);
            }
        }

        System.out.println("");
        System.out.println("These are the restaurants you have marked:");
        for (int i=0; i<foods.size(); i++) {
            Food oneFood = foods.get(i);
            String completionStatus = oneFood.getCompletionStatus();
            if (completionStatus.equals("C")) {
                System.out.println(++foodCount + ". " + oneFood);
            }
        }

        System.out.println("");
        System.out.println("These are the gifts you've marked:");
        for (int i=0; i<gifts.size(); i++) {
            Gift oneGift = gifts.get(i);
            String completionStatus = oneGift.getCompletionStatus();
            if (completionStatus.equals("C")) {
                System.out.println(++giftCount + ". " + oneGift);
            }
        }
    }
}
