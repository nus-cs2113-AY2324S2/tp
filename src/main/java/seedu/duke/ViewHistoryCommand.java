package seedu.duke;

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
            if (oneActivity.completionStatus.equals("C")) {
                System.out.println(++activityCount + ". " + oneActivity.description);
            }
        }

        System.out.println("");
        System.out.println("These are the restaurants you have marked:");
        for (int i=0; i<foods.size(); i++) {
            Food oneFood = foods.get(i);
            if (oneFood.completionStatus.equals("C")) {
                System.out.println(++foodCount + ". " + oneFood.description);
            }
        }

        System.out.println("");
        System.out.println("These are the gifts you've marked:");
        for (int i=0; i<gifts.size(); i++) {
            Gift oneGift = gifts.get(i);
            if (oneGift.completionStatus.equals("C")) {
                System.out.println(++giftCount + ". " + oneGift.description);
            }
        }
    }
}
