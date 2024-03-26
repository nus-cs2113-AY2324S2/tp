package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to generate a smart itinerary based on user preferences.
 * This command considers the user's preferred location and cuisine to create
 * a personalized itinerary that includes food and activities.
 */
public class GenerateSmartItineraryCommand extends Command{
    private static final Logger LOGGER = Logger.getLogger(GenerateItineraryCommand.class.getName());
    private String preferredLocation;
    private String preferredCuisine;

    /**
     * Creates a GenerateSmartItineraryCommand with specified user details.
     * @param userDetails The details of the user including preferred location and cuisine.
     */
    public GenerateSmartItineraryCommand(UserDetails userDetails) {
        this.preferredLocation = userDetails.getLocation();
        this.preferredCuisine = userDetails.getCuisine();
    }

    /**
     * Executes the smart itinerary generation process. It selects food and activities
     * based on the user's preferences and creates an itinerary. If the preferences
     * are too unique or if any error occurs, it logs a severe error and informs the user.
     * @param favourites The list of favourite items of the user.
     * @param foods The list of available foods.
     * @param activities The list of available activities.
     * @param ui The user interface to interact with the user.
     * @param storage The storage handler to save or load data.
     * @param userDetails The details of the user, including preferences.
     * @param gifts The list of gifts available for the user.
     * @throws FlirtForkException If an error specific to the Flirt Fork application occurs.
     */
    @Override
    public void execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui,
                        Storage storage, UserDetails userDetails, GiftList gifts) throws FlirtForkException {
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
