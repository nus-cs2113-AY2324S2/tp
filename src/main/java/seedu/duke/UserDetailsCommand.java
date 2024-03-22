package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

public class UserDetailsCommand extends Command {

    private static String HORIZONTAL = "____________________________________________________________";

    @Override
    public void execute(FavouritesList favouritesList, FoodList foodList, ActivityList activityList, Ui ui, Storage storage) {
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

        UserDetails userDetails = new UserDetails(name, age, gender, status);
        // Save the user details to a file using the Storage class
        storage.saveUserDetails(userDetails);

        ui.showMessage("User details saved successfully!");
    }
}
