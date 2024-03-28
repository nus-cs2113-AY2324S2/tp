package seedu.duke;

import seedu.duke.commands.AddActivityCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.DeleteFavouritesCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.FindFavouritesCommand;
import seedu.duke.commands.GenerateGiftCommand;
import seedu.duke.commands.GenerateIdeaCommand;
import seedu.duke.commands.GenerateItineraryCommand;
import seedu.duke.commands.GenerateSmartItineraryCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListFavouritesCommand;
import seedu.duke.commands.ListOptionsCommand;
import seedu.duke.commands.UserDetailsCommand;
import seedu.duke.commands.ViewHistoryCommand;
import seedu.duke.exceptions.FlirtForkEmptyException;
import seedu.duke.exceptions.FlirtForkException;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Scanner;

public class Parser {
    private static final String HORIZONTAL = "____________________________________________________________";
    private static Logger logger = Logger.getLogger("ParserLogger");

    public static Command parseCommand(String userInput, UserDetails userDetails) throws FlirtForkException {
        if (userInput.trim().isEmpty()) {
            throw new FlirtForkException("OOPS! Input cannot be empty! \n" + HORIZONTAL);
        }

        assert userDetails != null : "Input should not be empty";

        String commandType = userInput.split(" ")[0];
        String arguments = userInput.contains(" ") ? userInput.substring(userInput.indexOf(" ") + 1) : "";

        switch (commandType) {
        case "food":
            if (arguments.trim().isEmpty()) {
                throw new FlirtForkEmptyException();
            }
            return new AddFoodCommand(arguments);
        case "activity":
            if (arguments.trim().isEmpty()) {
                throw new FlirtForkEmptyException();
            }
            return new AddActivityCommand(arguments);
        case "favourites":
            return new ListFavouritesCommand();
        case "delete":
            try {
                int index = Integer.parseInt(arguments) - 1;
                if (index < 0) {
                    logger.log(Level.WARNING, "Index less than zero");
                    throw new FlirtForkException("OOPS! Index must be greater than 0! \n" +
                            HORIZONTAL);
                }
                return new DeleteFavouritesCommand(index);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Index invalid format");
                throw new FlirtForkException("OOPS! Invalid format, " +
                        "please specify task index correctly! \n" + HORIZONTAL);
            }
        case "find":
            if (arguments.trim().isEmpty()) {
                throw new FlirtForkException("OOPS! Please enter keyword(s) to find!");
            }
            return new FindFavouritesCommand(arguments);
        case "itinerary":
            if (arguments.trim().isEmpty()) {
                throw new FlirtForkEmptyException();
            }
            return new GenerateItineraryCommand(arguments);
        case "smart":
            if (!arguments.trim().isEmpty()) {
                throw new FlirtForkEmptyException();
            }
            return new GenerateSmartItineraryCommand(userDetails);
        case "idea":
            return new GenerateIdeaCommand();
        case "gift":
            return new GenerateGiftCommand();
        case "exit":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "me":
            return new UserDetailsCommand(); 
        case "history":
            return new ViewHistoryCommand();
        case "list":
            Ui.listCommand();
            Scanner scanner = new Scanner(System.in);
            String optionType = scanner.nextLine().toLowerCase();
            return new ListOptionsCommand(optionType);
        default:
            throw new FlirtForkException("Love is a language we all speak, but we didn't quite catch that. \n" +
                    "Try again? \n" + HORIZONTAL);
        }
    }

    public static Favourites parseFavourites(String line) {
        String[] parts = line.split(" \\| ");
        Favourites favourite = null;

        if ("F".equals(parts[0]) && parts.length >= 2) {
            favourite = new Food(parts[0], parts[1], parts[2], parts[3], parts[4]);
        } else if ("A".equals(parts[0]) && parts.length >= 2) {
            favourite = new Activity(parts[0], parts[1], parts[2], parts[3]);
        } else {
            assert false; // Throws AssertionError if favourite not created yet
        }

        return favourite;
    }

    public static Food parseFood(String line) {
        String[] parts = line.split(" \\| ");
        Food food;
        food = new Food(parts[0], parts[1], parts[2], parts[3], parts[4]);
        return food;
    }

    public static Activity parseActivity(String line) {
        String[] parts = line.split(" \\| ");
        Activity activity;
        activity = new Activity(parts[0], parts[1], parts[2], parts[3]);
        return activity;
    }

    public static Gift parseGift(String line) {
        String[] parts = line.split(" \\| ");
        Gift gift;
        gift = new Gift(parts[0], parts[1]);
        return gift;
    }
}
