package seedu.duke.commands;

import seedu.duke.ActivityList;
import seedu.duke.Command;
import seedu.duke.FavouritesList;
import seedu.duke.FoodList;
import seedu.duke.GiftList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.UserDetails;

/**
 * Represents a command to collect and store user details. This command prompts the user
 * for personal information such as name, age, gender, location, favourite cuisine, and
 * relationship status. Some details, like an anniversary date, are conditionally requested
 * based on the user's relationship status. The collected information is used to personalize
 * the application's services and recommendations.
 */
public class UserDetailsCommand extends Command {

    private static final String HORIZONTAL = "____________________________________________________________";

    /**
     * Executes the user detail collection process. This method interacts with the user
     * through the provided UI to gather personal information. It then saves these details
     * to the application's storage. Information collected includes name, age, gender,
     * location, favourite cuisine, relationship status, and potentially the anniversary date.
     * This information is essential for customizing the application experience and recommendations.
     *
     * @param favouritesList The user's list of favourite items (not directly used in this method).
     * @param foodList The list of food items (not directly used in this method).
     * @param activityList The list of activities (not directly used in this method).
     * @param ui The user interface to interact with the user for input and output.
     * @param storage The storage handler to save user details.
     * @param userDetails The UserDetails object to be populated with the user's information.
     * @param gifts The list of gifts (not directly used in this method).
     */
    @Override
    public void execute(FavouritesList favouritesList, FoodList foodList,
                        ActivityList activityList, Ui ui, Storage storage, UserDetails userDetails, GiftList gifts) {
        ui.showMessage("Please enter your name:");
        String name = ui.readCommand();
        ui.showMessage("Great! Hello there " + name + ", it's my pleasure to know you!"); 
        System.out.println(HORIZONTAL);

        ui.showMessage("Please enter your age:");
        String age = ui.readCommand();
        ui.showMessage("Wow, you're " + age + " years young! This might be handy information.");
        System.out.println(HORIZONTAL);

        ui.showMessage("Please enter your gender:");
        String gender = ui.readCommand();
        ui.showMessage("I see you're a " + gender + "!");
        System.out.println(HORIZONTAL);

        ui.showMessage("Where do you stay?\n");
        ui.showMessage("E: East");
        ui.showMessage("W: West");
        ui.showMessage("C: Central");
        ui.showMessage("S: South");
        ui.showMessage("NE: NorthEast");
        String location = ui.readCommand();
        ui.showMessage("Thanks! Don't worry, I won't let the rest know where you stay ;)!");
        System.out.println(HORIZONTAL);

        ui.showMessage("What is your favourite cuisine?\n");
        ui.showMessage("W: Western");
        ui.showMessage("F: Fusion");
        ui.showMessage("J: Japanese");
        ui.showMessage("C: Chinese");
        ui.showMessage("T: Thai");
        ui.showMessage("K: Korean");
        ui.showMessage("I: Italian");
        ui.showMessage("S: Spanish");
        String cuisine = ui.readCommand();
        ui.showMessage("Thanks, this will be useful...");
        System.out.println(HORIZONTAL);

        ui.showMessage("Please enter your relationship status:\n");
        ui.showMessage("Enter 'M' if you are Married");
        ui.showMessage("Enter 'R' if you are in a serious relationship");
        ui.showMessage("Enter 'F' if you are having a fling");
        ui.showMessage("Enter 'D' if you are dating/testing the waters");
        ui.showMessage("Enter 'S' if you are single and ready to mingle");
        ui.showMessage("Enter 'X' if you are single and only looking to hangout with friends");
        String status = ui.readCommand();
        ui.showMessage("Thanks for letting me know your relationship status! :)");
        System.out.println(HORIZONTAL);

        if (status.equals("M") || status.equals("R") || status.equals("D")) {
            String anniversary;
            ui.showMessage("Lucky you! Please enter your anniversary:");
            anniversary = ui.readCommand();
            userDetails.setAnniversary(anniversary);
        }

        userDetails.setName(name);
        userDetails.setAge(age);
        userDetails.setGender(gender);
        userDetails.setStatus(status);
        userDetails.setLocation(location);
        userDetails.setCuisine(cuisine);
        // Save the user details to a file using the Storage class
        storage.saveUserDetails(userDetails);

        ui.showMessage("User details saved successfully!");
    }
}
