package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GenerateSmartItineraryCommand extends Command{
    private static final Logger LOGGER = Logger.getLogger(GenerateItineraryCommand.class.getName());
    private String preferredLocation;
    private String preferredCuisine;

    public GenerateSmartItineraryCommand(UserDetails userDetails) {
        this.preferredLocation = userDetails.getLocation();
        this.preferredCuisine = userDetails.getCuisine();
    }

    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage) throws FlirtForkException {
        Food food1;
        Food food2;
        Activity activity1;
        Activity activity2;

        try {
            food1 = foods.getCustomisedFood(preferredLocation, preferredCuisine);
            activity1 = activities.getRandomActivity();
            // Ensure activity1 and activity2 are different
            do {
                food2 = foods.getCustomisedFood(preferredLocation, preferredCuisine);
            } while (food2.getDescription().equals(food1.getDescription()));
            // Ensure food1 and food2 are different
            do {
                activity2 = activities.getRandomActivity();
            } while (activity2.getDescription().equals(activity1.getDescription()));
            Itinerary itinerary = new Itinerary(food1, food2, activity1, activity2);
            System.out.println(itinerary);
        } catch (IllegalArgumentException e) {
            System.out.println(preferredCuisine);
            System.out.println(preferredLocation);
            System.out.println("You are too unique of an individual for smart itineraries! Sorry!!");
            LOGGER.log(Level.SEVERE, "Invalid arguments given");
        }
    }
    
}
