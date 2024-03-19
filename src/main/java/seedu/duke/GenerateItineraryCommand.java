package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;
import java.lang.IllegalArgumentException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GenerateItineraryCommand extends Command{
    private static final Logger LOGGER = Logger.getLogger(GenerateItineraryCommand.class.getName());
    private String preferredLocation;
    private String preferredPrice;

    public GenerateItineraryCommand(String description){
        String[] splitDescription = description.split(" ");
        this.preferredLocation = splitDescription[0];
        this.preferredPrice = splitDescription[1];
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        Food food1;
        Food food2;
        Activity activity1;
        Activity activity2;

        try {
            food1 = foods.getFilteredFood(preferredLocation, preferredPrice);
            activity1 = activities.getFilteredActivity(preferredLocation, preferredPrice);
            // Ensure activity1 and activity2 are different
            do {
                food2 = foods.getFilteredFood(preferredLocation, preferredPrice);
            } while (food2.getDescription().equals(food1.getDescription()));
            // Ensure food1 and food2 are different
            do {
                activity2 = activities.getFilteredActivity(preferredLocation, preferredPrice);
            } while (activity2.getDescription().equals(activity1.getDescription()));
            Itinerary itinerary = new Itinerary(food1, food2, activity1, activity2);
            System.out.println(itinerary);
        } catch (IllegalArgumentException e) {
            System.out.println("We could not generate a suitable itineray based on your inputs! Sorry!!");
            LOGGER.log(Level.SEVERE, "Invalid arguments given");
        }
    }
}
