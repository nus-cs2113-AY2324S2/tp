package seedu.duke;

import seedu.duke.exceptions.FlirtForkEmptyException;
import seedu.duke.exceptions.FlirtForkException;

public class Parser {
    private static final String HORIZONTAL = "____________________________________________________________";

    public static Command parseCommand(String userInput) throws FlirtForkException {
        if (userInput.trim().isEmpty()) {
            throw new FlirtForkException("OOPS! Input cannot be empty! \n" + HORIZONTAL);
        }

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
                    throw new FlirtForkException("OOPS! Index must be greater than 0! \n" +
                            HORIZONTAL);
                }
                return new DeleteFavouritesCommand(index);
            } catch (NumberFormatException e) {
                throw new FlirtForkException("OOPS! Invalid format, " +
                        "please specify task index correctly! \n" + HORIZONTAL);
            }
        case "find":
            if (arguments.trim().isEmpty()) {
                throw new FlirtForkException("OOPS! Please enter keyword(s) to find!");
            }
            return new FindFavouritesCommand(arguments);
        case "exit":
            return new ExitCommand();
        default:
            throw new FlirtForkException("Love is a language we all speak, but we didn't quite catch that. \n" +
                    "Try again? \n" + HORIZONTAL);
        }
    }

    public static Favourites parseFavourites(String line) {
        String[] parts = line.split(" \\| ");
        Favourites favourite = null;

        if ("F".equals(parts[0]) && parts.length >= 2) {
            favourite = new Food(parts[0], parts[1], parts[2]);
        } else if ("A".equals(parts[0]) && parts.length >= 2) {
            favourite = new Activity(parts[0], parts[1], parts[2]);
        }

        return favourite;
    }

    public static Food parseFood(String line) {
        String[] parts = line.split(" \\| ");
        Food food;
        food = new Food(parts[0], parts[1], parts[2]);
        return food;
    }

    public static Activity parseActivity(String line) {
        String[] parts = line.split(" \\| ");
        Activity activity;
        activity = new Activity(parts[0], parts[1], parts[2]);
        return activity;
    }
}
