package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class GenerateIdeaCommand extends Command {
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        String userSatisfied;

        do {
            Food food = foods.getRandomFood();
            Activity activity = activities.getRandomActivity();
            Idea idea = new Idea(food, activity);
            System.out.println(idea);
            System.out.println("Satisfied with the date idea? [Yes/No]");
            userSatisfied = ui.readCommand().toLowerCase();
            if (userSatisfied.equals("yes")) {
                System.out.println("That's great! Enjoy your date!");
                food.markComplete();
                activity.markComplete();
                break;
            } else {
                System.out.println("Regenerating a new date idea..");
            }
        } while (true);

        storage.saveFood(foods);
        storage.saveActivity(activities);
    }
}
