package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Flirt and Fork";
    private static final String HORIZONTAL = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greetingMessage(String anniversary) {
        System.out.println(HORIZONTAL);
        System.out.println("Hungry for love? You've come to the right place.\n" +
                "Welcome to " + BOT_NAME + " - where Cupid meets the chef!\n\n" +
                "Send me 'help' if you're new!");
        if (!anniversary.equals("N.A")) {
            System.out.println("\nRemember, your anniversary is on " + anniversary + " :)");
        }
        System.out.println(HORIZONTAL);
    }

    public void firstSetUpMessage() {
        System.out.println(HORIZONTAL);
        System.out.println("Hungry for love? You've come to the right place.\n" +
                "Welcome to " + BOT_NAME + " - where Cupid meets the chef!");
        System.out.println("Before we start, I would like to know you better!");
        System.out.println(HORIZONTAL);
    }

    public void exitMessage() {
        System.out.println("Love, like a good meal, is all about timing.\n" +
                "Keep your love simmering and your fork ready, " +
                "see you at the next course!");
    }

    public void helpMessage() {
        System.out.println(
            "To take a look at potential restaurants, activities, or gifts simply send me the following: \n" +
            "'list'\n\n" +
            "To let me know more about yourself, simply send me the following: \n" +
            "'me'\n\n" +
            "To generate a randomised date idea, simply send me the following: \n" +
            "'idea'\n\n" +
            "To generate a randomised gift idea, simply send me the following: \n" +
            "'gift'\n\n" +
            "To generate a suitable date itinerary based on your preferences, simply send me the following: \n" +
            "'itinerary' [space] '(preferred location)' [space] '(preferred price)'\n\n" +
            "To generate a smart itinerary, simply send me the following AFTER you have completed 'me': \n" +
            "'smart'\n\n" +
            "To add food to your favourites, simply send me the following: \n" +
            "'food' [space] '(name of eatery)' [space] '(location)' [space] '(price)'\n\n" +
            "To add an activity to your favourites, simply send me the following: \n" +
            "'activity' [space] '(name of activity)'[space] '(location)' [space] '(price)'\n\n" +
            "To find an entry from your favourites, simply send me the following: \n" +
            "'find' [space] '(keyword)'\n\n" +
            "To list out all your favourites, simply send me the following: \n" +
            "'favourites'\n\n" +
            "To delete an activity from your favourites, simply send me the following: \n" +
            "'delete' [space] '(index of entry)'\n\n" +   
            "To list out all your past date locations and restaurants, simply send me the following: \n" +
            "'history'\n\n" +         
            "To exit the program, simply send me the following: \n" +
            "'exit'\n\n" +

            "LEGEND (prices):\n" +
            "C: Cheap\n" +
            "B: Budget\n" +
            "A: Affordable\n" +
            "P: Pricey\n" +
            "S: Special Ocassions Only\n\n" +

            "LEGEND (locations):\n" +
            "E: East\n" +
            "W: West\n" +
            "C: Central\n" +
            "S: South\n" +
            "NE: NorthEast\n" +
            "ACC: Accessible (found in multiple places around SG)"
        );
    }

    public void errorMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showFavourite(String favourite) {
        System.out.println(favourite);
    }

    public void listFavourites(FavouritesList favouritesList) {
        System.out.println("Drumroll, please! Presenting the stars of your romantic sky: ");
        System.out.println(favouritesList.getFormattedFavourites());
    }

    public void showMatchingFavourites(ArrayList<Favourites> matchingFavourites) {
        if (matchingFavourites.isEmpty()) {
            System.out.println("No treasures found this time. Let's try another clue?");
        } else {
            System.out.println("Eureka! Your cupid's arrow hit the target! We found these matches for you:");
            for (int i = 0; i < matchingFavourites.size(); i++) {
                System.out.println((i + 1) + ". " + matchingFavourites.get(i));
            }
        }
    }

    public static void listFood() {
        System.out.println("HMMMM Let's see what food is theree: ");
    }

    public static void listActivities() {
        System.out.println("What are some activities you can do as a couple? Let's see.." );
    }

    public static void listGifts() {
        System.out.println("Peek into Cupid's own gift collection!" );
    }

    public static void listCommand() {
        System.out.println("Looking for ideas to spice up your date night?");
        System.out.println("Choose from the following options:");
        System.out.println("1. List out delicious dining options (type 'food')");
        System.out.println("2. Discover exciting activities to do together (type 'activities')");
        System.out.println("3. Unwrap joy with our curated list of gifts that'll make hearts flutter! (type 'gifts')");
        System.out.println("What's your pleasure?");
    }

    public void ideaSatisfiedErrorMessage() {
        System.out.println("Sorry, I didn't quite understand that :(");
        System.out.println("Please enter either yes or no");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
